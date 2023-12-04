package com.jay.springkeeptrack.controllers.dto;

import com.jay.springkeeptrack.entity.todo.ToDoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ToDoRequest {
    @NotBlank(message="item must not be blank")
    private String item;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotBlank(message = "added must not be blank")
    private String added;
    @NotBlank(message = "status must not be blank")
    private String status;

}
