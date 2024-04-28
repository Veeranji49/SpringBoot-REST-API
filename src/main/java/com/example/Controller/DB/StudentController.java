package com.example.Controller.DB;

import com.example.Entity.DB.Student;
import com.example.Service.DB.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //**   http://localhost:9999/springboot_restapi/api/v1/students/add-student
    @PostMapping(value="/add-student")
    public ResponseEntity<Student> addStudent(@RequestBody  Student student) {
        Student std = studentService.createStudent(student);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    //**  http://localhost:9999/springboot_restapi/api/v1/students/update-student/3
    @PutMapping(value="/update-student/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody  Student student, @PathVariable long id) {
        Student std = studentService.updateStudent(student, id);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    //**   http://localhost:9999/springboot_restapi/api/v1/students/delete-student/3
    @DeleteMapping(value="/delete-student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //**  http://localhost:9999/springboot_restapi/api/v1/students/get-student/2
    @GetMapping(value="/get-student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student std = studentService.getStudent(id);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    //**  http://localhost:9999/springboot_restapi/api/v1/students/getall-students
    @GetMapping(value="/getall-students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
