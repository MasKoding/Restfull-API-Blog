package org.spring.learn.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CobaArticle {
    
    @Id
    @SequenceGenerator(
        name="article_sequence",
        sequenceName = "article_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy=GenerationType.SEQUENCE,
        generator = "article_sequence"
    )
    private Long id;

    private String title;

    
    @ManyToOne
    @JoinColumn(name="article_id")
    private CobaCategory cobaCategory;
}
