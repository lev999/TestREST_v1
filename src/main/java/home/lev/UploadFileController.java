package home.lev;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@Controller
@RequestMapping(value = "/upload")
class UploadFileController{

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String responseToGet(){
        return "please upload file to the same address";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String responseToPost(
            @RequestParam("fileName")MultipartFile file,
            @RequestParam(value = "name")String name
    ) throws IOException {

        if(name.isEmpty())
            name=file.getOriginalFilename();

        if(!file.isEmpty()&&file.getBytes().length<100){
            try {

                byte[] biteArray = file.getBytes();
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(name)));
                outputStream.write(biteArray);
                outputStream.flush();
                outputStream.close();
                return "uploaded success!"+" name:"+name;
            } catch (IOException e) {
                return "error in file "+ e.getMessage();
            }
        }
         else
            return "file is big or empty";
    }

}

