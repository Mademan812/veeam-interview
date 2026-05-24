package cz.volejnik.veeam.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetStatus {
    AVAILABLE("available"), PENDING("pending"), SOLD("sold");

    @JsonValue
    private final String value;

    @JsonCreator
    public static PetStatus fromValue(String value) {
        return valueOf(value.toUpperCase());
    }
}
