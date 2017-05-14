package com.rachel.cider.jpa.repositories.impl;

import com.rachel.cider.jpa.repositories.custom.AccessTokenCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by rachel.tung on 14/05/2017.
 */

public class AccessTokenRepoImpl implements AccessTokenCustomRepo {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccessTokenRepoImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate  = mongoTemplate;
    }

}
