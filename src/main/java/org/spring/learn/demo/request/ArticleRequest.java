package org.spring.learn.demo.request;

import lombok.Data;
import org.spring.learn.demo.entity.Category;

@Data
public class ArticleRequest {
    
    private Long id;
    
    private String title;

    private String content;

    private Integer category_id;

    private String tag;

}
