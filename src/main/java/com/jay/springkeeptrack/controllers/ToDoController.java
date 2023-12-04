package com.jay.springkeeptrack.controllers;

import com.jay.springkeeptrack.controllers.dto.ToDoRequest;
import com.jay.springkeeptrack.entity.todo.ToDo;
import com.jay.springkeeptrack.entity.user.Role;
import com.jay.springkeeptrack.service.ToDoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path="/todos")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<ToDo> getTodo() throws Exception {
        return toDoService.getUserTodos();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public void createTodo(@Valid @RequestBody ToDoRequest request) throws Exception {
        toDoService.createTodo(request);
    }

    @DeleteMapping("/{todoId}")
    @PreAuthorize("hasAuthority('USER')")
    public void deleteTodo(@PathVariable() Integer todoId) throws Exception{
        toDoService.deleteTodo(todoId);
    }

    @PutMapping("/{todoId}")
    @PreAuthorize("hasAuthority('USER')")
    public void updateTodo(@PathVariable() Integer todoId, @Valid @RequestBody ToDoRequest request) throws Exception {
        toDoService.updateTodo(todoId, request);
    }

}
