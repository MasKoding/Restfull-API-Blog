package org.spring.learn.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.spring.learn.demo.dao.ArticleDao;
import org.spring.learn.demo.dao.CategoryDao;
import org.spring.learn.demo.dao.CobaArticleDao;
import org.spring.learn.demo.dao.CobaCategoryDao;
import org.spring.learn.demo.entity.Articles;
import org.spring.learn.demo.entity.Category;
import org.spring.learn.demo.entity.CobaArticle;
import org.spring.learn.demo.entity.CobaCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private CobaArticleDao cobaArticleDao;

	@Autowired
	private CobaCategoryDao cobaCategoryDao;

	@Test
	void contextLoads() {
	}

	@Test
	void insertArticlesTests(){
		Articles articles = new Articles();
        Category category = new Category();
        category.setId(1L);
        category.setCategory_desc("Programming");

		articles.setTitle("Introduce Spring Boot");
		articles.setContent("TES");
		articles.setCategory(category);
		articles.setTag("Java,Spring Framework");

		articleDao.save(articles);

	}

	@Test
	void insertCategoryTests(){
		Category categories1 = new Category();
		categories1.setCategory_desc("Programming");

		Category categories2 = new Category();
		categories2.setCategory_desc("Vlog");

		
		Category categories3 = new Category();
		categories3.setCategory_desc("Technology");

		
		Category categories4 = new Category();
		categories4.setCategory_desc("Hiburan");

		
		Category categories5 = new Category();
		categories5.setCategory_desc("Story");

		


	}

	@Test
	void testQueryLike(){
		List<Articles> articleByTag = articleDao.findByTagContainingIgnoreCase("Java");

		assertEquals(3, articleByTag.size());
		System.out.println(articleByTag.size());

	}



}
