package cz.volejnik.veeam.dto;

import lombok.Builder;

@Builder
public record Tag(Long id, String name) {
}
