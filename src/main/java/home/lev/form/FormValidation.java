package home.lev.form;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "form")
public class FormValidation {
    @ResponseBody
    @RequestMapping(value = "check",method = RequestMethod.GET)
    public String check(){
        return "check";
    }

    @ResponseBody
    @RequestMapping(value = "supplyNameAge",method = RequestMethod.POST)
    public String supplyNameAge(@Valid PersonToValid personToValid, BindingResult bindingResult){
        StringBuilder out=new StringBuilder();
        if(!bindingResult.hasErrors()){

            out
                    .append("name=").append(personToValid.getName()).append(",")
                    .append("age=").append(personToValid.getAge());

            return out.toString();
        }
        return out.append("error "+bindingResult.getTarget()).toString();
    }
}
