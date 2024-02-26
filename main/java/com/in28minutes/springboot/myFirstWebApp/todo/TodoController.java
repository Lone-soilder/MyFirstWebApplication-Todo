package com.in28minutes.springboot.myFirstWebApp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService ;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap){

        List<Todo> todos= todoService.findByUsername("in28minutes");
        modelMap.addAttribute("todos" , todos);
        return "listTodos";  //this will search listTodos.jsp in return
    }
}