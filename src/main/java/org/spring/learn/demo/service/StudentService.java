package org.spring.learn.demo.service;

import java.util.List;

import org.spring.learn.demo.dao.StudentDao;
import org.spring.learn.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;
    
    public List<Student> getListOfStudent(){
        return studentDao.findAll(); 
    }

    public Student create(Student student){
        return studentDao.save(student);
    }
}
