package cz.volejnik.veeam.dto;

import cz.volejnik.veeam.data.OrderStatus;
import lombok.Builder;

/**
 * DTO representing order payload.
 *
 * @param id long value
 * @param petId int value
 * @param quantity int value
 * @param shipDate String value
 * @param status {@link OrderStatus} enum value
 * @param complete boolean value
 */
@Builder
public record Order(Long id, Integer petId, Integer quantity, String shipDate, OrderStatus status, Boolean complete) {
}
