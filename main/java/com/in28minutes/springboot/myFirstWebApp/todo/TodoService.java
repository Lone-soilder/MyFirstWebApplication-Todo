package com.in28minutes.springboot.myFirstWebApp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        todos.add(new Todo(++todosCount , "in28minutes" ,"learn AWS" , LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todosCount , "in28minutes" , "learn Java" ,LocalDate.now().plusYears(1),false));
    }

    public List<Todo> findByUsername(String username){

        Predicate<? super Todo> predicate =
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username , String description , LocalDate targetDate , boolean done){
        todos.add(new Todo(++ todosCount , username ,description , targetDate,done ));

    }

    public void deleteById(int id){
        //we will use this predicate to match the condition first before we remove the id from list

        Predicate<? super Todo> predicate =
                todo -> todo.getId()==id;
        todos.removeIf(predicate);

    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate =
                todo -> todo.getId()==id;
        Todo todo=  todos.stream().filter(predicate).findFirst().get();

        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        addTodo(todo.getUsername(),todo.getDescription(),todo.getTargetDate(),todo.isDone());
    }
}
