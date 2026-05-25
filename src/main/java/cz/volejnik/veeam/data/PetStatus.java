package cz.volejnik.veeam.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the allowed values of "status" field within the context of pets.
 */
@Getter
@AllArgsConstructor
public enum PetStatus {
    AVAILABLE("available"), PENDING("pending"), SOLD("sold");

    @JsonValue
    private final String value;

    /**
     * Ensures smooth mapping of lowercase values into enum field values by a default {@link tools.jackson.databind.ObjectMapper}.
     *
     * @param value lowercase String value
     * @return {@link PetStatus} enum value
     */
    @JsonCreator
    public static PetStatus fromValue(String value) {
        return valueOf(value.toUpperCase());
    }
}
