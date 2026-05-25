package cz.volejnik.veeam;

import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import cz.volejnik.veeam.config.ApplicationProperties;
import cz.volejnik.veeam.config.PropertiesBuilder;
import cz.volejnik.veeam.reporting.CustomLogManager;
import cz.volejnik.veeam.reporting.CustomLogger;
import cz.volejnik.veeam.utils.HttpPrettyPrinter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    private static final CustomLogger LOGGER = CustomLogManager.getLogger(BaseTest.class);
    private static final ApplicationProperties applicationProperties = PropertiesBuilder.buildRunConfig();

    @BeforeClass
    public void setUpClass() {
        RestAssured.baseURI = applicationProperties.getSutUrl();
        RestAssured.filters((requestSpec, responseSpec, ctx) -> {
            Response response = ctx.next(requestSpec, responseSpec);
            String log = """
                    REQUEST:
                     Method: %s
                     URI: %s
                     Headers: %s
                     Cookies: %s
                     Body: %s
                    
                    RESPONSE:
                     Status: %s
                     Response time: %s
                     Headers: %s
                     Body: %s
                    """.formatted(
                            requestSpec.getMethod(),
                            requestSpec.getURI(),
                            requestSpec.getHeaders(),
                            requestSpec.getCookies(),
                            HttpPrettyPrinter.body(requestSpec),
                            response.statusCode(),
                            HttpPrettyPrinter.time(response),
                            response.getHeaders(),
                            response.body().asPrettyString()
                    );
            LOGGER.info(log);
            return response;
        });
        if(Boolean.parseBoolean(System.getProperty("contracts", "false"))) {
            RestAssured.filters(new OpenApiValidationFilter("https://petstore.swagger.io/v2/swagger.yaml"));
        }
    }
}
