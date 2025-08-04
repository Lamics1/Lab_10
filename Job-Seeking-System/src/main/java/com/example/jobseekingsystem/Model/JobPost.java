package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "salary >0")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(50) not null")
    @NotEmpty(message = "title must be not empty!")
    @Size(min = 5, message = "must be more than 4 characters")
    private String title;

    @Column(columnDefinition = "varchar(255) not null")
    @NotEmpty(message = "description must be not empty!")
    private String description;


    @Column(columnDefinition = "varchar(50) not null")
    @NotEmpty(message = "description must be not empty!")
    private String location;


    @Column(columnDefinition = "DOUBLE not null")
    @PositiveOrZero(message = "salary must be a non-negative number")
    @NotNull(message = "Salary must be not null")
    private Double salary;


    private LocalDate postingDate;
}
