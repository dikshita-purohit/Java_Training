package com.example.firstPrj.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.firstPrj.student.Students;

@Repository
public interface StudentRepositry extends JpaRepository<Students, Long> {

}