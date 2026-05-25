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

import java.io.File;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

/**
 * Only implemented example set of tests for demonstration purposes, rest omitted for timeboxing purposes.
 */
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

    @Test(dataProvider = "providePetIds")
    public void testGetPetById(Object petId, int expectedStatus) {
        Pet pet = RestAssured.given().with()
                .when().get(BASE_PATH + "/" + petId)
                .then().statusCode(expectedStatus).and().body("$", not(empty())).and().extract().as(Pet.class);
        Assert.assertNotNull(pet);
    }

    @Test
    public void testUpdatePet() {
        Pet pet = Pet.builder()
                .id(0L)
                .category(Category.builder().id(999L).name("MyTestCategory").build())
                .name("MyTestPet")
                .photoUrls(new String[]{"photo1", "photo2"})
                .tags(new Tag[]{
                        Tag.builder().id(888L).name("MyTestTag1").build(),
                        Tag.builder().id(999L).name("MyTestTag2").build()
                })
                .status(PetStatus.AVAILABLE).build();
        pet = RestAssured.given().with().body(pet).contentType(ContentType.JSON)
                .when().post(BASE_PATH)
                .then().statusCode(200).and().body("$", not(empty())).and().extract().as(Pet.class);
        //Validate state of pet in DB
        Pet updatedPet = Pet.builder()
                .id(pet.id())
                .category(pet.category())
                .name("MyUpdatedPet")
                .photoUrls(new String[]{"UpdatedPhoto1"})
                .tags(pet.tags())
                .status(PetStatus.PENDING).build();
        updatedPet = RestAssured.given().with().body(updatedPet).contentType(ContentType.JSON)
                .when().put(BASE_PATH)
                .then().statusCode(200).and().body("$", not(empty())).and().extract().as(Pet.class);
        Assert.assertNotEquals(updatedPet, pet, "The pet must differ from the original.");
        //Validate state of pet in DB
    }

    @Test
    public void testPostUploadImage() {
        Pet pet = Pet.builder()
                .id(0L)
                .category(Category.builder().id(999L).name("MyTestCategory").build())
                .name("MyTestPet")
                .photoUrls(new String[]{"photo1", "photo2"})
                .tags(new Tag[]{
                        Tag.builder().id(888L).name("MyTestTag1").build(),
                        Tag.builder().id(999L).name("MyTestTag2").build()
                })
                .status(PetStatus.AVAILABLE).build();
        pet = RestAssured.given().with().body(pet).contentType(ContentType.JSON)
                .when().post(BASE_PATH)
                .then().statusCode(200).and().body("$", not(empty())).and().extract().as(Pet.class);
        //Validate state of pet in DB

        File img = new File("src/test/resources/test-data/test.jpg");

        Pet updatedPet = RestAssured.given().with().multiPart("file", img).contentType(ContentType.MULTIPART)
                .when().post(BASE_PATH + "/" + pet.id() + UPLOAD_IMAGE_PATH)
                .then().statusCode(200).and().body("$", not(empty())).and().extract().as(Pet.class);
        Assert.assertNotEquals(updatedPet, pet, "The pet must differ from the original.");
        //Validate state of pet in DB
    }

    @Test(dataProvider = "provideStatusData")
    public void testGetPetByStatus(String status, int expectedStatus) {
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
    private static Object[][] providePetIds() {
        //In practice fetch valid IDs from DB
        return new Object[][]{
                {2218L, 200},
                {Integer.MAX_VALUE, 404},
                {-1L, 400},
                {0.5, 400},
                {"abc", 400},
                {"123", 400},
                {Long.MAX_VALUE, 400}
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
