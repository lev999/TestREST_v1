package home.lev;

import home.lev.Services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/student")
public class ControllerStudent {

    @Autowired
    IStudentService studentService;

    @RequestMapping("/")
    public Student getStudent(@RequestParam(value = "name",required = false,defaultValue = "noneName")String name){
        return studentService.getStudent(name);
    }

    @RequestMapping("{name}")
    public Student getStudentByName(@PathVariable("name") String name){
        return studentService.getStudent(name);
    }

}
