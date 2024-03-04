package com.in28minutes.springboot.myFirstWebApp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;
    //initialize static variable;

    static {
        todos.add(new Todo(++todosCount , "in28minutes" ,"learn AWS" , LocalTime.now().plusHours(1),false));
        todos.add(new Todo(++todosCount , "in28minutes" , "learn Java" ,LocalTime.now().plusHours(1),false));
    }

    public List<Todo> findByUsername(String username){
        return todos;
    }

    public void addTodo(String username , String description , LocalTime targetDate , boolean done){
        todos.add(new Todo(++ todosCount , username ,description , targetDate,done ));

    }

    public void deleteById(int id){
        //we will use this predicate to match the condition first before we remove the id from list

        Predicate<? super Todo> predicate =
                todo -> todo.getId()==id;
        todos.removeIf(predicate);

    }
}
