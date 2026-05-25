package cz.volejnik.veeam.data;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents the allowed values of "status" field within the context of orders.
 */
public enum OrderStatus {
    PLACED, APPROVED, DELIVERED;

    /**
     * Ensures smooth mapping of lowercase values into enum field values by a default {@link tools.jackson.databind.ObjectMapper}.
     *
     * @param value lowercase String value
     * @return {@link OrderStatus} enum value
     */
    @JsonCreator
    public static OrderStatus fromValue(String value) {
        return valueOf(value.toUpperCase());
    }
}
