package home.lev.mapping;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "param",method = RequestMethod.GET, params = "foo")
    public  String requestParam(){
        return "with param";
    }

    @RequestMapping(value = "param",method = RequestMethod.GET, params = "!foo")
    public String noParam(){
        return "no param";
    }

    @RequestMapping(value = "header",method = RequestMethod.GET, headers = "headerFoo")
    public String withHeader(){
        return "with header";
    }

    @RequestMapping(value = "header",method = RequestMethod.GET, headers ={"!headerFoo","MyHeader"} )
    public String noHeaders(){
        return "no header";
    }

    @RequestMapping(
            value = "jsonPost",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public String jsonPost(@RequestBody Kot kot){
        return kot.toString();
    }

    @RequestMapping(value = "produces",produces = MediaType.APPLICATION_JSON_VALUE)
    public Kot getJson(){
        return new Kot();
    }

    @RequestMapping(value = "produces",produces = MediaType.APPLICATION_XML_VALUE)
    public Kot getXml(){
        return new Kot();
    }

    @RequestMapping(value = "produces",produces = MediaType.TEXT_PLAIN_VALUE)
    public String JsonGetAsText(){
        return (new Kot()).toString();
    }
}
