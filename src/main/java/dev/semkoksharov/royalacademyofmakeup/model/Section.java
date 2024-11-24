package dev.semkoksharov.royalacademyofmakeup.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToOne(mappedBy = "section")
    private Quiz quiz;


    // Геттеры, сеттеры и конструкторы
}