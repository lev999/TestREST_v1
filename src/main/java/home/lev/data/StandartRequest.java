package home.lev.data;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
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

    @RequestMapping(value = "readWithInputStream", method = RequestMethod.POST)
    public String readWithInputStream(InputStream inputStream) throws IOException {
        return "readWithInputStream:"+(new String(FileCopyUtils.copyToByteArray(inputStream)));
    }

    @RequestMapping(value = "readWithReader",method = RequestMethod.POST)
    public String readWithReader(Reader reader) throws IOException {
        return "readWithReader:"+ FileCopyUtils.copyToString(reader);
    }

    @RequestMapping(value = "writeResponseWithStream", method = RequestMethod.GET)
    public void writeResponseWithStream(OutputStream outputStream) throws IOException {
        outputStream.write("writeResponseWithStream".getBytes());
    }

    @RequestMapping(value = "writeResponseWithWrite",method = RequestMethod.GET)
    public void writeResponseWithWrite(Writer writer) throws IOException {
        writer.write("writeResponseWithWrite");
    }

    @RequestMapping(value = "getSession",method = RequestMethod.GET)
    public String getSession(HttpSession httpSession){
        return "getSession:"+httpSession.toString();
    }
}
//look https://karthikg.wordpress.com/2009/11/08/learn-to-customize-spring-mvc-controller-method-arguments/