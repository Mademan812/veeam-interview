package cz.volejnik.veeam.dto;

import cz.volejnik.veeam.data.OrderStatus;
import lombok.Builder;

@Builder
public record Order(Long id, Integer petId, Integer quantity, String shipDate, OrderStatus status, Boolean complete) {
}
