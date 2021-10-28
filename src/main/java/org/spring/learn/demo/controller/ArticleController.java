package org.spring.learn.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.learn.demo.request.ArticleRequest;
import org.spring.learn.demo.response.ArticleResponse;
import org.spring.learn.demo.response.BaseResponse;
import org.spring.learn.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/articles")
public class ArticleController {
    Logger logger =  LoggerFactory.getLogger(ArticleController.class);


    @Autowired
    private ArticleService articleService;

     @GetMapping("/list")
     public <T> ResponseEntity <T> getListArticles(
             @RequestParam("category") String category,
             @RequestParam("tag") String tag
     ){
         try {
             List<ArticleResponse> articleResponses = null;
             if(category.equals("") || tag.equals("")){
                 articleResponses = articleService.listOfArticles();
             }else{
                articleResponses = articleService.getArticlesByCategoryAndTag(category,tag);
             }

             BaseResponse baseResponse = successResponse(articleResponses);

             return new ResponseEntity<>((T) baseResponse, HttpStatus.OK);
            
         } catch (Exception e) {
             //TODO: handle exception
             logger.info("error:", e.getMessage());
             e.printStackTrace();

             BaseResponse baseResponse = failedResponse();
             return new ResponseEntity<T>((T) baseResponse, HttpStatus.BAD_REQUEST);
         }
     }




    @RequestMapping(value="",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public <T> ResponseEntity <T> createArticles(@RequestBody ArticleRequest articleRequest){
        try {
            
            articleService.createArticles(articleRequest);

            BaseResponse baseResponse = successResponse(null);
            
            return new ResponseEntity<>((T) baseResponse, HttpStatus.OK);
            
        } catch (Exception e) {
            //TODO: handle exception
            logger.info("error:", e.getMessage());
            e.printStackTrace();
            
            BaseResponse baseResponse = failedResponse();
           

            return new ResponseEntity<T>((T) baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public <T> ResponseEntity <T> editArticles(@PathVariable("id") Long id,@RequestBody ArticleRequest articleRequest){
        try {
            
            articleService.updateArticles(articleRequest, id);;

            BaseResponse baseResponse = successResponse(null);
            
            return new ResponseEntity<>((T) baseResponse, HttpStatus.OK);
            
        } catch (Exception e) {
            //TODO: handle exception
            logger.info("error:", e.getMessage());
            e.printStackTrace();
            
            BaseResponse baseResponse = failedResponse();
           

            return new ResponseEntity<T>((T) baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public <T> ResponseEntity <T> getListArticlesByTag(@RequestParam("tag") String tag){
        try {
            
            List<ArticleResponse> articleResponses = articleService.getArticlesByTag(tag);
            BaseResponse baseResponse = successResponse(articleResponses);

            return new ResponseEntity<>((T) baseResponse, HttpStatus.OK);
            
        } catch (Exception e) {
            //TODO: handle exception
            logger.info("error:", e.getMessage());
            e.printStackTrace();

            BaseResponse baseResponse = failedResponse();
            return new ResponseEntity<T>((T) baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public <T> ResponseEntity <T> deleteArticles(@PathVariable("id") Long id){
        try {
            
            articleService.deleteArticle(id);
            BaseResponse baseResponse = successResponse(null);

            return new ResponseEntity<>((T) baseResponse, HttpStatus.OK);
            
        } catch (Exception e) {
            //TODO: handle exception
            logger.info("error:", e.getMessage());
            e.printStackTrace();

            BaseResponse baseResponse = failedResponse();
            return new ResponseEntity<T>((T) baseResponse, HttpStatus.BAD_REQUEST);
        }
    }

    public BaseResponse successResponse(List<?> data){

        BaseResponse baseResponse = new BaseResponse<>();
        baseResponse.setCode(HttpStatus.OK.value());
        baseResponse.setStatus(HttpStatus.OK.name());
        baseResponse.setData(data);

        return baseResponse;
    }

    public BaseResponse failedResponse(){
   
        BaseResponse baseResponse = new BaseResponse<>();
        baseResponse.setCode(HttpStatus.BAD_REQUEST.value());
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.name());
        baseResponse.setData(new ArrayList<>());

        return baseResponse;
    }

}
