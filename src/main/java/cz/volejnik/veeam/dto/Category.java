package cz.volejnik.veeam.dto;

import lombok.Builder;

@Builder
public record Category(Long id, String name) {
}
