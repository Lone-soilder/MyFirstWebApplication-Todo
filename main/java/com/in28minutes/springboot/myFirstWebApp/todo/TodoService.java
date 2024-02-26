package com.in28minutes.springboot.myFirstWebApp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    //initialize static variable;

    static {
        todos.add(new Todo(1 , "in28minutes" ,"learn AWS" , LocalTime.now().plusHours(1),false));
        todos.add(new Todo(2 , "in28minutes" , "learn Java" ,LocalTime.now().plusHours(1),false));
    }

    public List<Todo> findByUsername(String username){
        return todos;
    }
}
