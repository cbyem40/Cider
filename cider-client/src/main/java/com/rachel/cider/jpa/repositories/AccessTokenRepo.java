package com.rachel.cider.jpa.repositories;

import com.rachel.cider.jpa.entities.AccessToken;
import com.rachel.cider.jpa.repositories.custom.AccessTokenCustomRepo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rachel.tung on 14/05/2017.
 */
public interface AccessTokenRepo extends MongoRepository<AccessToken, Long>, AccessTokenCustomRepo{
    AccessToken findFirstByUserId(String userId);
    AccessToken findByUserIdAndAccessToken(String userId, String accessToken);
}
