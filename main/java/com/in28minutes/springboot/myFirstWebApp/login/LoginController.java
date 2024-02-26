package com.in28minutes.springboot.myFirstWebApp.login;


import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")  //should use in all the controller
public class LoginController {


    private AuthenticationService authenticationService ;

    //Constructor injection auto wiring the object for authentication service

    public LoginController(AuthenticationService authenticationService) {
        super();
        this.authenticationService = authenticationService;
    }


    //jsp path - src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp

    //http://localhost:8080/login?name=biswajit
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String loginPage(){
        //@RequestParam String name , ModelMap modelMap as parameter
        //modelMap.put("name",name);
        return "login";
    }

    @RequestMapping(value = "login" , method = RequestMethod.POST)
    public String welcomePage(@RequestParam String name , @RequestParam String password ,
                              ModelMap modelMap){

        //Authentication
        //name- Biswajit
        //Password- sahoo

        if (authenticationService.authentication(name,password)){
            modelMap.put("name",name);
            modelMap.put("password",password);
            return "welcome";
        }

        modelMap.put("errorMessage","wrong credential");
        return "login";
    }
}
