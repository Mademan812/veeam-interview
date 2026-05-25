package cz.volejnik.veeam.dto;

import lombok.Builder;

/**
 * DTO representing category payload.
 *
 * @param id long value
 * @param name string value
 */
@Builder
public record Category(Long id, String name) {
}
