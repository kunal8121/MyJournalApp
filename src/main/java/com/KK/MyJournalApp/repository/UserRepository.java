package com.KK.MyJournalApp.repository;

import com.KK.MyJournalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);

     void deleteByUserName(String userName);
}
