package com.api.LibraryApi.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Dni cannot be empty")
    @Column(name = "dni", length = 8)
    private String dni;

    @NotNull(message = "Firstname cannot be empty")
    @Column(name = "firstname")
    private String firstname;

    @NotNull(message = "Lastname cannot be empty")
    @Column(name = "lastname")
    private String lastname;


    @Column(name = "course")
    private String course;

    @NotNull(message = "Phone cannot be empty")
    @Column(length = 10)
    private String phone;

    @NotNull(message = "Address cannot be null")
    @Column(name = "address")
    private String address;

    @Column(nullable = false, unique = true)
    @Email(message = "Has to be an email format")
    @NotBlank
    private String email;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private Boolean deleted = Boolean.FALSE;
}
