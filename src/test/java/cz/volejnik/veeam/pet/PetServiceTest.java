package cz.volejnik.veeam.pet;

import cz.volejnik.veeam.BaseTest;
import cz.volejnik.veeam.data.PetStatus;
import cz.volejnik.veeam.dto.Category;
import cz.volejnik.veeam.dto.Pet;
import cz.volejnik.veeam.dto.Tag;
import cz.volejnik.veeam.listener.CustomTestListener;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

@Listeners(CustomTestListener.class)
public class PetServiceTest extends BaseTest {
    private static final String BASE_PATH = "/pet";
    private static final String FIND_BY_STATUS_PATH = "/findByStatus";
    private static final String UPLOAD_IMAGE_PATH = "/uploadImage";

    @Test(dataProvider = "providePostData")
    public void testPostPet(Object payload, int expectedStatus) {
        RestAssured.given().with().body(payload).contentType(ContentType.JSON)
                .when().post(BASE_PATH)
                .then().statusCode(expectedStatus).and().body("$", not(empty())).and().extract().as(Pet.class);
        if(expectedStatus == 200) {
            //Assert correct data in the DB
        }
    }

    @Test(dataProvider = "provideStatusData")
    public void findPetByStatus(String status, int expectedStatus) {
        Pet[] pets = RestAssured.given().with().param("status", status)
                .when().get(BASE_PATH + FIND_BY_STATUS_PATH)
                .then().statusCode(expectedStatus).and().body("$", not(empty())).and().extract().as(Pet[].class);
        Assert.assertTrue(pets.length > 0, "At least one pet is expected.");
    }

    @DataProvider
    private static Object[][] providePostData() {
        Pet valid = Pet.builder()
                .id(0L)
                .category(Category.builder().id(999L).name("MyTestCategory").build())
                .name("MyTestPet")
                .photoUrls(new String[]{"photo1", "photo2"})
                .tags(new Tag[]{
                        Tag.builder().id(888L).name("MyTestTag1").build(),
                        Tag.builder().id(999L).name("MyTestTag2").build()
                })
                .status(PetStatus.AVAILABLE).build();
        String invalid = "{\"invalid\":\"invalid\"}";
        return new Object[][]{
                {valid, 200},
                {invalid, 405}
        };
    }

    @DataProvider
    private static Object[][] provideStatusData() {
        return new Object[][]{
                {PetStatus.AVAILABLE.getValue(), 200},
                {PetStatus.PENDING.getValue(), 200},
                {PetStatus.SOLD.getValue(), 200},
                {"", 400},
                {"invalid", 400}
        };
    }
}
