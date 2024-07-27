package com.khasanov.projectspring1.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "task", schema = "todo")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(100) NOT NULL")
    private String description;

    @Column(columnDefinition = "int(11) NOT NULL")
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
