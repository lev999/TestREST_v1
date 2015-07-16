package home.lev.data;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping(value = "data/standartRequest")
public class StandartRequest {

    @RequestMapping(value = "PrincipalLocale",method = RequestMethod.GET)
    public String PrincipalLocale(HttpServletRequest request,Locale locale, Optional<Principal> principal){
        StringBuilder buffer=new StringBuilder("PrincipalLocale ");

        buffer
                .append("request:").append(request).append("\n")
                .append("locale:").append(locale).append("\n")
        ;
        principal.ifPresent(pr ->buffer.append("principal:").append(pr).append(",").append(pr.getName()).append("\n"));
        return buffer.toString();
    }

    @RequestMapping(value = "check",method = RequestMethod.GET)
    public String check(){
        return "check";
    }
}
