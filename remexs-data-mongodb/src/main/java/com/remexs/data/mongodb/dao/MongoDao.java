package com.remexs.data.mongodb.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.repository.NoRepositoryBean;
@NoRepositoryBean
public interface MongoDao<T> extends MongoRepository<T, String> {

}
