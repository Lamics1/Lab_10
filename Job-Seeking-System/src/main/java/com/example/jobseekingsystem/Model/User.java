package com.example.jobseekingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "role='JOB_SEEKER'or role='EMPLOYER' ")
@Check(constraints = " age > 21 ")
@Check(constraints = "email REGEXP '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$'")
@Check(constraints = "name NOT LIKE '%[0-9]%'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(50) not null")
    @NotEmpty(message = "Name must be not empty!")
    @Size(min = 5, message = "must be more than 4 ")
    private String name;

    @Column(columnDefinition = "varchar(70) unique")
    @Email(message = "Must be a valid email format")
    private String email;

    @Column(columnDefinition = "varchar(50) not null")
    @NotEmpty(message = "Cannot be Empty")
    private String password;

    @Column(columnDefinition = "INT not null")
    @NotNull(message = "Cannot be null")
    @Min(value = 22, message = "Age must be greater than 21")
    private Integer age;

    @NotEmpty(message = "must be Not empty")
    @Pattern(regexp = ("JOB_SEEKER|EMPLOYER"))
    @Column(columnDefinition = "varchar(11) not null ")
    private String role;

}
