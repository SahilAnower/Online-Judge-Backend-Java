package com.sahil.online.judge.dao;

import com.sahil.online.judge.entity.LikeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeCountRepository extends JpaRepository<LikeCount, Long> {
}
