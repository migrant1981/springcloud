package springcloud.zuul_server.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("import")
public class ZuulThreeController {
	
	 @GetMapping("{name}")
    public String ZuulImportData(@PathVariable("name") String name) {
        return "this file is  " + name + "  this is import data";
    }

}
