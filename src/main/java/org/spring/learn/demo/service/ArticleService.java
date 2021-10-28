package org.spring.learn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.spring.learn.demo.dao.ArticleDao;
import org.spring.learn.demo.dao.CategoryDao;
import org.spring.learn.demo.entity.Articles;
import org.spring.learn.demo.entity.Category;
import org.spring.learn.demo.request.ArticleRequest;
import org.spring.learn.demo.response.ArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CategoryDao categoryDao;
    
    public void createArticles(ArticleRequest articleRequest){
        Articles articles = new Articles();
        String article_id = articleRequest.getCategory_id().toString();

        Category category = categoryDao.getById(Long.getLong(article_id));
        articles.setTitle(articleRequest.getTitle());
        articles.setContent(articleRequest.getContent());
        articles.setCategory(category);
        articles.setTag(articleRequest.getTag());

        articleDao.save(articles);
    }

    public void updateArticles(ArticleRequest articleRequest,Long id){
       Optional<Articles> getArticle = articleDao.findById(id);
    
        if(getArticle.isPresent()){
            Articles articles = getArticle.get();
            String article_id = articleRequest.getCategory_id().toString();

            Category category = categoryDao.getById(Long.getLong(article_id));
            articles.setTitle(articles.getTitle());
            articles.setContent(articleRequest.getContent());
            articles.setCategory(category);
            articles.setTag(articleRequest.getTag());

            articleDao.save(articles);
    
        }
        
    }

    public List<ArticleResponse> listOfArticles(){
        
        List<Articles> articles = articleDao.findAll();
        
        List<ArticleResponse> articleResponse = new ArrayList<>();
        
        articles.stream().forEach(i->{
            ArticleResponse setArticleResponse = new ArticleResponse();
            setArticleResponse.setId(i.getId());
            setArticleResponse.setTitle(i.getTitle());
            setArticleResponse.setContent(i.getContent());
            setArticleResponse.setCategory(i.getCategory());
            setArticleResponse.setTag(i.getTag());

            articleResponse.add(setArticleResponse);
          });

          return articleResponse;
    }


    public List<ArticleResponse> getArticlesByCategoryAndTag(String category,String tag){
        List<Articles> getArticles = articleDao.findByCategoryAndTagContainingIgnoreCase(category,tag);
        List<ArticleResponse> articleResponse = new ArrayList<>();

        getArticles.stream().forEach(i->{
            ArticleResponse setArticleResponse = new ArticleResponse();
            setArticleResponse.setId(i.getId());
            setArticleResponse.setTitle(i.getTitle());
            setArticleResponse.setContent(i.getContent());
            setArticleResponse.setCategory(i.getCategory());
            setArticleResponse.setTag(i.getTag());

            articleResponse.add(setArticleResponse);
        });

        return articleResponse;
    }

    public List<ArticleResponse> getArticlesByTag(String tag){
        List<Articles> getArticles = articleDao.findByTagContainingIgnoreCase(tag);
        List<ArticleResponse> articleResponse = new ArrayList<>();

        getArticles.stream().forEach(i->{
            ArticleResponse setArticleResponse = new ArticleResponse();
            setArticleResponse.setId(i.getId());
            setArticleResponse.setTitle(i.getTitle());
            setArticleResponse.setContent(i.getContent());
            setArticleResponse.setCategory(i.getCategory());
            setArticleResponse.setTag(i.getTag());

            articleResponse.add(setArticleResponse);
        });
        
        return articleResponse;
    }

    public List<ArticleResponse> getArticlesByCategory(String category){
        List<Articles> getArticles = articleDao.getArticleByCategory(category);
        List<ArticleResponse> articleResponse = new ArrayList<>();

        getArticles.stream().forEach(i->{
            ArticleResponse setArticleResponse = new ArticleResponse();
            setArticleResponse.setId(i.getId());
            setArticleResponse.setTitle(i.getTitle());
            setArticleResponse.setContent(i.getContent());
            setArticleResponse.setCategory(i.getCategory());
            setArticleResponse.setTag(i.getTag());

            articleResponse.add(setArticleResponse);
        });

        return articleResponse;
    }
    

    public void deleteArticle(Long id){
        articleDao.deleteById(id);
    }
    
}
