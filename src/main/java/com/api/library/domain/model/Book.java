package com.api.library.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@ToString
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title cannot be empty")
    @Column(name = "title", length = 70)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @NotNull(message = "Number of pages cannot be empty")
    @Column(name = "number_of_pages")
    private String numberOfPages;

    @NotNull(message = "Amount cannot be empty")
    @Column(name = "amount ")
    private int amount ;

    @NotNull(message = "Return date cannot be empty")
    @Column(name = "edition_date")
    private LocalDate returnDate;

    @NotNull(message = "Image URL is required")
    @Column(name = "image")
    private String image;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    private Boolean deleted = Boolean.FALSE;
}
