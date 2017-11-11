package com.turvo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * The Class SimpleMongoConfig.
 */
@Configuration
public class SimpleMongoConfig {
  
    /**
     * Mongo.
     *
     * @return the mongo
     * @throws Exception the exception
     */
    public @Bean Mongo mongo() throws Exception {
        return new MongoClient("localhost", 27017);
    }
 
    /**
     * Mongo template.
     *
     * @return the mongo template
     * @throws Exception the exception
     */
    public @Bean MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "test");
    }
    
}