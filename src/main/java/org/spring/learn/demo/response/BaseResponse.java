package org.spring.learn.demo.response;

import java.util.List;

import lombok.Data;

@Data
public class BaseResponse <T>{
    private Integer code;
    private String status;
    private List<T> data;
}
