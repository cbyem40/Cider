package com.rachel.cider.service;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by rachel.tung on 13/05/2017.
 */
@Validated
public interface AccessTokenService {
    void createAccessToken(@NotBlank(message = "No user id entered. Unable to insert.") final  String userId,
                           @NotBlank(message = "No accessToken entered. Unable to insert.") final String accessToken,
                           @NotNull(message = "No expire in entered. Unable to insert.")final Integer expiresIn);

    void createAccessToken(@NotBlank(message = "No user id entered. Unable to insert.") final  String userId,
                           @NotBlank(message = "No accessToken entered. Unable to insert.") final String accessToken);

    String loginFacebook();
}
