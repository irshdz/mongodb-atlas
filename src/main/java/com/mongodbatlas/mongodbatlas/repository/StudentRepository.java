package com.mongodbatlas.mongodbatlas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodbatlas.mongodbatlas.model.Student;

public interface StudentRepository extends MongoRepository<Student,String> {
    
}
