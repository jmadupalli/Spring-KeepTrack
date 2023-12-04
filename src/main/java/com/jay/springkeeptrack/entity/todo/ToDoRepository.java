package com.jay.springkeeptrack.entity.todo;

import com.jay.springkeeptrack.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    List<ToDo> findByUserIdOrderByAdded(Integer userId);

    Optional<ToDo> findByUserIdAndId(Integer userId, Integer id);
}
