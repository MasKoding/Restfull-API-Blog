package org.spring.learn.demo.dao;

import org.spring.learn.demo.entity.CobaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CobaCategoryDao extends JpaRepository<CobaCategory,Long> {

}
