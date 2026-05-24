package cz.volejnik.veeam.dto;

import cz.volejnik.veeam.data.PetStatus;
import lombok.Builder;

@Builder
public record Pet(Long id, Category category, String name, String[] photoUrls, Tag[] tags, PetStatus status) {
}
