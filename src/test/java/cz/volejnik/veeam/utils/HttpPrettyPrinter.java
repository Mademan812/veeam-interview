package cz.volejnik.veeam.utils;

import cz.volejnik.veeam.reporting.CustomLogManager;
import cz.volejnik.veeam.reporting.CustomLogger;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import lombok.experimental.UtilityClass;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

/**
 * Utility class that makes logging complex data easier and more efficient.
 */
@UtilityClass
public class HttpPrettyPrinter {
    private static final CustomLogger LOGGER = CustomLogManager.getLogger(HttpPrettyPrinter.class);

    /**
     * Constructs the request's body as a prettified JSON String.
     *
     * @param request {@link FilterableRequestSpecification}
     * @return Prettified JSON String
     */
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

    /**
     * Constructs response time into meaningful, readable formatted String. If time was less than 1000ms, returns
     * a number of milliseconds (for example 395ms). If time was more than 1000ms, returns a number of seconds
     * (for example 1.405s).
     *
     * @param response {@link Response}
     * @return Formatted String of time elapsed
     */
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
