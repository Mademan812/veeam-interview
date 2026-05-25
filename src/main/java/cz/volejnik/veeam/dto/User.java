package cz.volejnik.veeam.dto;

import lombok.Builder;

/**
 * DTO representing user payload.
 *
 * @param id long value
 * @param username String value
 * @param firstName String value
 * @param lastName String value
 * @param email String value
 * @param password String value
 * @param phone String value
 * @param userStatus int value
 */
@Builder
public record User(Long id, String username, String firstName, String lastName, String email, String password, String phone, Integer userStatus) {
}
