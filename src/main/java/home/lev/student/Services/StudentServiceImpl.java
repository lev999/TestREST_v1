package home.lev.student.Services;

import home.lev.student.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl implements IStudentService {
    @Override
    public Student getStudent(String name) {
        return new Student(name,123);
    }
}
