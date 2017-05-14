package com.rachel.cider.controller;

import com.rachel.cider.service.AccessTokenService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/v1/api/socialMedia")
@Validated
public class SocialMediaController {

    @Autowired
    private AccessTokenService accessTokenService;

    /**
     * To trigger the accessToken flow.
     * @param userId
     * @param accessToken
     * @param expiresIn
     */
    @ResponseBody
    @PostMapping("/users/{userId}/accessToken")
    @ResponseStatus(value = HttpStatus.OK)
    void createAccessToken(@NotBlank @PathVariable("userId") final String userId, @NotBlank final String accessToken, @NotNull final Integer expiresIn){
        accessTokenService.createAccessToken(userId, accessToken, expiresIn);
    }

    /**
     * Use facebook account to login
     * @param http
     */
    @ResponseBody
    @PostMapping("/facebook/login")
    @ResponseStatus(value = HttpStatus.OK)
    public void facebookLogin(HttpServletResponse http){
        redirectToFacebookOauth(http, accessTokenService.loginFacebook());
    }

    private void redirectToFacebookOauth(HttpServletResponse http, String redirectUrl)
    {
        try {
            http.sendRedirect(redirectUrl);
        } catch (IOException ex) {

        }
    }

    /**
     * to receive the callback accessToken from Facebook
     * @param accessToken
     */
    @ResponseBody
    @GetMapping("/oauth/callback")
    @ResponseStatus(value = HttpStatus.OK)
    void oauthCallback(@NotBlank @RequestParam("code") String accessToken){
        System.out.println(accessToken);
        accessTokenService.createAccessToken(UUID.randomUUID().toString(), accessToken);
    }

}