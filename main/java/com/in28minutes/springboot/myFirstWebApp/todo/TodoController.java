package com.in28minutes.springboot.myFirstWebApp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import java.time.LocalDate;
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

    @RequestMapping(value = "add-todo" , method = RequestMethod.GET)  // this method is to show the new page where you can give todo details
    public String showNewTodoPage(ModelMap modelMap){
        Todo todo = new Todo(0 , (String) modelMap.get("name")," ", LocalDate.now().plusYears(1),false);
        modelMap.put("todo",todo);
        return "todo";  //this will search todo.jsp in return where you can edit or add a todo action.
    }

    @RequestMapping(value = "add-todo" , method = RequestMethod.POST)  // this method works when you click submit on your todo - page
    public String addNewTodo(ModelMap modelMap , @Valid Todo todo , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "todo";
        }
        todoService.addTodo((String) modelMap.get("name"),todo.getDescription(), todo.getTargetDate().plusYears(1),false);
        return "redirect:list-todos";  //this will redirect to the page "list-todos" URL  in return
    }

    @RequestMapping("delete-todo")
    public  String  deleteTodo(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:list-todos";

    }

    @RequestMapping(value = "update-todo" , method = RequestMethod.GET) // this method works when you click update button
    public  String  showUpdateTodoPage(@RequestParam int id , ModelMap modelMap){
        Todo todo = todoService.findById(id);
        modelMap.addAttribute("todo",todo);
        return "todo";

    }

    @RequestMapping(value = "update-todo" , method = RequestMethod.POST) // this method works when you click on submit button of todo page
    public String updateTodo(ModelMap modelMap , @Valid Todo todo , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "todo";
        }
        todoService.updateTodo(todo);
        todo.setUsername((String) modelMap.get("name"));
        return "redirect:list-todos";  //this will redirect to the page "list-todos" URL  in return
    }

}
