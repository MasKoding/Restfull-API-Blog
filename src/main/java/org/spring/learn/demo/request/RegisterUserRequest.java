package org.spring.learn.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;   
}
