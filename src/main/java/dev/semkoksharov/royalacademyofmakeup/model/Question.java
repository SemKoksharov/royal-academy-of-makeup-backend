package dev.semkoksharov.royalacademyofmakeup.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ElementCollection
    private List<String> options; // Варианты ответа

    @ElementCollection
    private List<Integer> correctAnswers; // Индексы правильных ответов

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    // Геттеры, сеттеры и конструкторы
}
