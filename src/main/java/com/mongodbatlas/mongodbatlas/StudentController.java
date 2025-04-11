package com.mongodbatlas.mongodbatlas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodbatlas.mongodbatlas.model.Student;
import com.mongodbatlas.mongodbatlas.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
@GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable String id){
        return studentRepository.findById(id);
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);}
        @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id,@RequestBody Student studentDetails)
        {
            return studentRepository.findById(id).map(student->{
                student.setName(studentDetails.getName());
                student.setAge(studentDetails.getAge());
                student.setCourse(studentDetails.getCourse());
                return studentRepository.save(student);
            }).orElseGet(()->{
                studentDetails.setId(id);
                return studentRepository.save(studentDetails);
            });
        }
        @DeleteMapping("/{id}")
        public String deleteStudent(@PathVariable String id){
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        }
    }


