package home.lev.mapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mapping-class/*")
public class MappingController {

    @RequestMapping("path")
    public String mappedByPath(){
        return "mapped by path";
    }

    @RequestMapping(value = "path/*", method = RequestMethod.GET)
    public String mappedByWildcard(HttpServletRequest request){
        return request.getRequestURI();
    }
}
