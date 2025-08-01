package com.lmao.demo.service;

import com.lmao.demo.enity.Student;
import com.lmao.demo.repo.StudentRepo;

import java.util.List;

public class StudentService {

    private StudentRepo studentRepo = new StudentRepo();

    public List<Student> getStudent() {
        return studentRepo.getStudent();

        // controller <--- service <---- repo
    }

    public void addStudent(Student student) {

        studentRepo.addStudent(student);
    }

    public void deleteStudent(Long id) {

        studentRepo.deleteStudent(id);
    }

    public Student getStudentById(Long id) {
        return studentRepo.getStudentById(id);
    }

    public void updateStudent(Student student) {

        studentRepo.updateStudent(student);
    }
}
