package com.cmpt276.student_database.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt276.student_database.models.Student;
import com.cmpt276.student_database.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepo;
    @GetMapping("/students/home")
    public String displayHome(){
        return "students/home";
    }
    @GetMapping("/students/view")
    public String getAllStudents(Model model){
        System.out.println("Getting students");
        // get students from database
        List<Student> students = studentRepo.findAll();
        // end of database call
        model.addAttribute("stud", students);
        return "students/showAll";
    }
    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        if(newStudent.get("name") != "" && newStudent.get("weight") != ""
        && newStudent.get("height") != "" && newStudent.get("gpa") != ""
        && newStudent.get("hairColor") != ""){
            System.out.println("Add Student");
            String newName = newStudent.get("name");
            int newHeight = Integer.parseInt(newStudent.get("height"));
            int newWeight = Integer.parseInt(newStudent.get("weight"));
            int newGpa = Integer.parseInt(newStudent.get("gpa"));
            String newHairColor = newStudent.get("hairColor");
            studentRepo.save(new Student(newName, newWeight, newHeight, newHairColor, newGpa));
            //Created new student
            response.setStatus(201);
        }
        else{
            System.out.println("Error!");
            response.setStatus(404);
        }
        return "students/home";
    }
    @PostMapping("/students/remove")
    public String removeStudent(@RequestParam String studentId, HttpServletResponse response){
        if(studentId != ""){
            System.out.println("Remove Student");
            studentRepo.deleteById(Integer.parseInt(studentId));
            //Removed student
            response.setStatus(201);
        }
        else{
            System.out.println("Error!");
            response.setStatus(404);
        }
        return "students/home";
    }
    @PostMapping("/students/edit")
    public String editStudent(@RequestParam Map<String, String> changedStudent, HttpServletResponse response){
        System.out.println("Edited Student");
        Student student = studentRepo.findBysId(Integer.parseInt(changedStudent.get("sId")));  
        if(student != null){
            student.setGpa(Float.parseFloat(changedStudent.get("gpa")));
            student.setWeight(Float.parseFloat(changedStudent.get("weight")));
            student.setHeight(Float.parseFloat(changedStudent.get("height")));
            student.setName(changedStudent.get("name"));
            student.setHairColor(changedStudent.get("hairColor"));
            studentRepo.save(student);
            //Edited student    
            response.setStatus(201);
        }
        else{
            System.out.println("Error!");
            response.setStatus(404);
        }
        return "students/home";
    }
}
