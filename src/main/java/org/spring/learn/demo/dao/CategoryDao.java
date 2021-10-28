package org.spring.learn.demo.dao;


import org.spring.learn.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Long>{
    
}
