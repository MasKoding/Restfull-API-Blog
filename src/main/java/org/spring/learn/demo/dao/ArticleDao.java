package org.spring.learn.demo.dao;

import java.util.List;

import org.spring.learn.demo.entity.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleDao extends JpaRepository<Articles,Long>{
    List<Articles> findByTagContainingIgnoreCase(String tag);

    @Query(value = "SELECT * FROM articles a inner join category c on a.category_id = c.id where a.category_id=:category",nativeQuery = true)
    List<Articles> getArticleByCategory(@Param("category") String category);

    List<Articles> findByCategoryAndTagContainingIgnoreCase(String category, String tag);
}
