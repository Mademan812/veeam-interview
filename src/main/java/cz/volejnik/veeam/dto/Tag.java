package cz.volejnik.veeam.dto;

import lombok.Builder;

/**
 * DTO representing tag payload.
 *
 * @param id long value
 * @param name String value
 */
@Builder
public record Tag(Long id, String name) {
}
