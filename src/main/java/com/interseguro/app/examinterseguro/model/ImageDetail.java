package com.interseguro.app.examinterseguro.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "image_detail")
public class ImageDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImageDetail;

    @Column(nullable = false, length = 100)
    private Integer value;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "id_image", nullable = false, foreignKey = @ForeignKey(name = "fk_imageDetail_image"))
    private Image image;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
