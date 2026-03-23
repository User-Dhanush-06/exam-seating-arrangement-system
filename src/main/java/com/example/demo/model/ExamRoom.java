package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exam_rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Integer rowCount;

    @Column(nullable = false)
    private Integer columnCount;

    private Integer capacity;

    @PrePersist
    @PreUpdate
    public void calculateCapacity() {
        if (rowCount != null && columnCount != null) {
            this.capacity = rowCount * columnCount;
        }
    }
}