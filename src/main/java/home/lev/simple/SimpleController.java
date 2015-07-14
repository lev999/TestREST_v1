package home.lev.simple;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SimpleController {

    @RequestMapping(value = "hello", method = RequestMethod.GET,headers = "Accept=text/plain")
    public String sayHello(){
        return "Hello world";
    }
}
