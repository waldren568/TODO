package com.company.TODO.List.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Entity
@Data
public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Max 100 chars")
    @Column(unique = true)
    private String title;

    @Size(max = 500)
    private String description;

    private boolean completed;
}
