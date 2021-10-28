package org.spring.learn.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.learn.demo.entity.Student;
import org.spring.learn.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping(value="")
    public List<Student> getStudentList(){
        return studentService.getListOfStudent();    
    }
    @PostMapping(value="")
    public <T> ResponseEntity<T> registerStudent(@RequestBody Student student){
        Map<String,Object> result = new HashMap<>();
        try {

            if(!student.getEmail().isEmpty()){
                Student responseStudent = studentService.create(student);
                result.put("code",HttpStatus.OK);
                result.put("status",HttpStatus.OK.toString());
                result.put("data", responseStudent);

            }
            else{
                result.put("code",HttpStatus.BAD_REQUEST);
                result.put("status",HttpStatus.BAD_REQUEST.toString());
                result.put("data", new ArrayList<>());
                
                return new ResponseEntity<T>((T) result, HttpStatus.OK);
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return new ResponseEntity<T>((T) result, HttpStatus.OK);
    }
}
