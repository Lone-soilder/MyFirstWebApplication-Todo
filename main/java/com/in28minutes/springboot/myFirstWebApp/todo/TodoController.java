package com.in28minutes.springboot.myFirstWebApp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import java.time.LocalTime;
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

    @RequestMapping(value = "add-todo" , method = RequestMethod.GET)
    public String showNewTodo(ModelMap modelMap){
        Todo todo = new Todo(0 , (String) modelMap.get("name"),"",LocalTime.now().plusHours(1),false);
        modelMap.put("todo",todo);
        return "todo";  //this will search listTodos.jsp in return
    }

    @RequestMapping(value = "add-todo" , method = RequestMethod.POST)
    public String addNewTodo(ModelMap modelMap , @Valid Todo todo , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "todo";
        }
        todoService.addTodo((String) modelMap.get("name"),todo.getDescription(), LocalTime.now().plusHours(1),false);
        return "redirect:list-todos";  //this will redirect to the page "list-todos" URL  in return
    }

    @RequestMapping("delete-todo")
    public  String  deleteTodo(@RequestParam int id){
        //delete todo with specific id
        todoService.deleteById(id);
        return "redirect:list-todos";

    }
}
