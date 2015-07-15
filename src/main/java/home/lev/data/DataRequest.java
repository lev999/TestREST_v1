package home.lev.data;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/data")
public class DataRequest {

    @RequestMapping(value = "oneParamRequest",method = RequestMethod.GET)
    public String oneParamRequest(@RequestParam(value = "oneParam") String paramMy){
        return paramMy;
    }
    @RequestMapping(value = "manyParamRequest",method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String manyParamRequest(RequestParams requestParams){
        return requestParams.toString();
    }
    @RequestMapping(value = "pathParamRequest/{myParam1}/{myParam2}",method = RequestMethod.GET)
    public String pathParamRequest(@PathVariable(value = "myParam1") String myParamOne, @PathVariable(value = "myParam2") String myParamTwo){
        return myParamTwo+" " +myParamOne;
    }



    @RequestMapping(value = "/pets/{petId}", method = RequestMethod.GET)
    public String findPet(@PathVariable String petId,
                          @MatrixVariable(value = "q") int q,
                          @MatrixVariable(value = "r")int r
    ) {
        return String.valueOf(q)+String.valueOf(r);
        // petId == 42
        // q == 11
    }

    @RequestMapping(value = "matrixWithTwoPathVar/{AA1}/{BB1}")
    public String matrixWithTwoPathVar(
            @MatrixVariable(pathVar = "AA1", value = "a")String a1,
            @MatrixVariable(pathVar = "BB1",value = "a")String a2
    ){
        return a1+a2;
    }

}
