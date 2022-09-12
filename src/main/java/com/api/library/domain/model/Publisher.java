package com.api.library.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE news SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Entity
@Table(name = "publishers")
public class Publisher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Column(name = "name")
    @NotBlank(message = "Name cannot be empty")
    @NotEmpty(message = "Name cannot be null")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "Name cannot contain numbers or characters other than letters")
    private String name;

    @NotNull(message = "Address cannot be null")
    @Column(name = "address")
    private String address;

    @Column(length = 50)
    private String phone;

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
