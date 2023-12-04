package com.jay.springkeeptrack.entity.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jay.springkeeptrack.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="todos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="item")
    private String item;

    @Column(name="added")
    private LocalDateTime added;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private ToDoStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="user_id", nullable = false)
    @JsonIgnore
    private User user;


}
