package cz.volejnik.veeam.data;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderStatus {
    PLACED, APPROVED, DELIVERED;

    @JsonCreator
    public static OrderStatus fromValue(String value) {
        return valueOf(value.toUpperCase());
    }
}
