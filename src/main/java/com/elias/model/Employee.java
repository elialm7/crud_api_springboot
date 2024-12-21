package com.elias.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tbl_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "El nombre es un campo obligatorio")
    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "age")
    @JsonProperty("age")
    private Long age = 0l;

    @Column(name = "location")
    @JsonProperty("location")
    private String location;

    @NotNull(message = "El email es un campo obligatorio")
    @Email(message = "El email tiene que tener un formato valido")
    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "department")
    @JsonProperty("department")
    private String department;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "udpated_at")
    private Date updatedAt;

}
