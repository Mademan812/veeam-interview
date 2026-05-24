package cz.volejnik.veeam.utils;

import cz.volejnik.veeam.reporting.CustomLogManager;
import cz.volejnik.veeam.reporting.CustomLogger;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import lombok.experimental.UtilityClass;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@UtilityClass
public class HttpPrettyPrinter {
    private static final CustomLogger LOGGER = CustomLogManager.getLogger(HttpPrettyPrinter.class);

    public static String body(FilterableRequestSpecification request) {
        String result;
        Object body = request.getBody();
        if(body == null) {
            result = "null";
        } else {
            ObjectMapper mapper = new ObjectMapper();
            if (body instanceof String) {
                body = mapper.readTree((String) body);
            }
            try {
                result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            } catch (JacksonException e) {
                LOGGER.warn(e.getMessage(), e);
                result = body.toString();
            }
        }
        return result;
    }

    public static String time(Response response) {
        String result;
        long time = response.getTime();

        if(time > 1000) {
            result = String.valueOf(time / 1000) + "s";
        } else {
            result = String.valueOf(time) + "ms";
        }
        return result;
    }
}
