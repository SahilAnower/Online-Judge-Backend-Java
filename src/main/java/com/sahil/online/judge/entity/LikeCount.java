package com.sahil.online.judge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "like_count")
public class LikeCount {
    private Long likes;
    private Long dislikes;
    // reference to comment, solution, problem
}
