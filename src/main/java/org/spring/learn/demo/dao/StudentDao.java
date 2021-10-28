package org.spring.learn.demo.dao;

import org.spring.learn.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,Long>{
    
}
