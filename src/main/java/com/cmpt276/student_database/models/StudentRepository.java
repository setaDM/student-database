package com.cmpt276.student_database.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer>{
    List<Student> findByName(String name);
    Student findBysId(int sId);
}
