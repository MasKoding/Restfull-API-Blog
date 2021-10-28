package org.spring.learn.demo.response;

import lombok.Data;
import org.spring.learn.demo.entity.Category;

@Data
public class ArticleResponse {
    private Long id;
    
    private String title;

    private String content;

    private Category category;

    private String tag;
}
