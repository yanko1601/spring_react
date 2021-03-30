package spring.react.jwt.Web.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @CrossOrigin
    @GetMapping("/")
    public String helloMessage() {
        return "<h1>Hello Tennis League Fans</h1>";
    }
}
