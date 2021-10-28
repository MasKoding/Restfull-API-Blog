package org.spring.learn.demo.dao;

import java.util.List;

import org.spring.learn.demo.entity.CobaArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CobaArticleDao extends JpaRepository<CobaArticle,Long>{
    
    @Query(value = "SELECT * FROM coba_article a inner join coba_category c on a.article_id = c.id where a.article_id=:category",nativeQuery = true)
    List<CobaArticle> getByCategory(@Param("category") Integer category);
}
