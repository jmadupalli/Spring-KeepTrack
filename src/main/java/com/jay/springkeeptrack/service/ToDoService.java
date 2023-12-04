package com.jay.springkeeptrack.service;

import com.jay.springkeeptrack.controllers.dto.ToDoRequest;
import com.jay.springkeeptrack.entity.todo.ToDo;
import com.jay.springkeeptrack.entity.todo.ToDoRepository;
import com.jay.springkeeptrack.entity.todo.ToDoStatus;
import com.jay.springkeeptrack.entity.user.User;
import com.jay.springkeeptrack.entity.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    public List<ToDo> getUserTodos() throws Exception{
        User user =  getUserFromContext();

        List<ToDo> todos = toDoRepository.findByUserIdOrderByAdded(user.getId());
        return todos;
    }

    public void createTodo(ToDoRequest request) throws Exception {
        User user = getUserFromContext();

        ToDo todo = ToDo.builder().item(request.getItem()).status(ToDoStatus.valueOf(request.getStatus())).added(LocalDateTime.parse(request.getAdded())).user(user).build();
        toDoRepository.save(todo);

    }

    public void deleteTodo(Integer id) throws Exception {
        User user = getUserFromContext();

        Optional<ToDo> todo = toDoRepository.findByUserIdAndId(user.getId(), id);

        todo.ifPresent(toDo -> toDoRepository.deleteById(toDo.getId()));
    }

    public void updateTodo(Integer id, ToDoRequest request) throws Exception {
        User user = getUserFromContext();
        Optional<ToDo> todo = toDoRepository.findByUserIdAndId(user.getId(), id);
        todo.ifPresent(toDo -> {
            toDo.setItem(request.getItem());
            toDo.setStatus(ToDoStatus.valueOf(request.getStatus()));
           toDoRepository.save(toDo);
        });
    }

    private User getUserFromContext() throws Exception {
        Optional<User> user =  userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if(user.isEmpty())
            throw new Exception("Invalid User");

        return user.get();
    }
}
