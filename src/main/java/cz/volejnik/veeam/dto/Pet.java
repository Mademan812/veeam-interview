package cz.volejnik.veeam.dto;

import cz.volejnik.veeam.data.PetStatus;
import lombok.Builder;

/**
 * DTO representing pet payload.
 *
 * @param id long value
 * @param category {@link Category} instance
 * @param name String value
 * @param photoUrls Array of String values
 * @param tags Array of {@link Tag} instances
 * @param status {@link PetStatus} enum value
 */
@Builder
public record Pet(Long id, Category category, String name, String[] photoUrls, Tag[] tags, PetStatus status) {
}
