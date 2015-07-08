package home.lev;

import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
@RequestMapping("/upload/")
public class UploadFileController {

    @RequestMapping(method = RequestMethod.POST)
    public void getFile(@RequestParam MultipartFile file, Model model) throws IOException {

        model.addAttribute("originalName",file.getOriginalFilename());
        model.addAttribute("sentName",file.getName());
        model.addAttribute("fileContext",readFromFile(file));
    }

    private String readFromFile(MultipartFile file) throws IOException {
        InputStream inputStream =file.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine())!=null)
            stringBuilder.append(line);

        return stringBuilder.toString();
    }
}

