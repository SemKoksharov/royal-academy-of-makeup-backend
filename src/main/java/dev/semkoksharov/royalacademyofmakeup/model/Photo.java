package dev.semkoksharov.royalacademyofmakeup.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    // Геттеры, сеттеры и конструкторы
}