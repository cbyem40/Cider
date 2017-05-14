package com.rachel.cider.service.impl;

import com.rachel.cider.jpa.entities.AccessToken;
import com.rachel.cider.jpa.repositories.AccessTokenRepo;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;
import com.restfb.scope.UserDataPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



/**
 * Created by rachel.tung on 13/05/2017.
 */
@Service
public class AccessTokenServiceImpl implements com.rachel.cider.service.AccessTokenService {

    @Autowired
    AccessTokenRepo accessTokenRepo;

    @Value("${application.connect.facebook.client.id}")
    private String facebookClientId;

    @Override
    public void createAccessToken(String userId, String accessToken, Integer expiresIn) {
        AccessToken accessTokenEntity = new AccessToken();
        accessTokenEntity.setUserId(userId);
        accessTokenEntity.setAccessToken(accessToken);
        accessTokenEntity.setExpiresIn(expiresIn);
        accessTokenRepo.save(accessTokenEntity);
    }

    @Override
    public void createAccessToken(String userId, String accessToken) {
        AccessToken accessTokenEntity = new AccessToken();
        accessTokenEntity.setUserId(userId);
        accessTokenEntity.setAccessToken(accessToken);
        accessTokenRepo.save(accessTokenEntity);
    }

    @Override
    public String loginFacebook() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(UserDataPermissions.USER_ABOUT_ME);
        scopeBuilder.addPermission(UserDataPermissions.USER_FRIENDS);

        FacebookClient client = new DefaultFacebookClient(Version.VERSION_2_6);
        String loginDialogUrlString = client.getLoginDialogUrl(facebookClientId, "http://localhost:8080/v1/api/socialMedia/oauth/callback", scopeBuilder);
        return loginDialogUrlString;
    }
}
