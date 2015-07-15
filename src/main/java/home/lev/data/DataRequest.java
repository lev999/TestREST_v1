package home.lev.data;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
    public String findPet(@PathVariable String petId, @MatrixVariable int q) {
        return petId+q;
        // petId == 42
        // q == 11
    }
}
