package main.java.home.lev.Services;

import main.java.home.lev.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl implements IStudentService {
    @Override
    public Student getStudent(String name) {
        return new Student(name,123);
    }
}
