package com.noticeboard.Dao;

import com.noticeboard.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Topic findById(Integer id);

    List<Topic> findByIdBetween(Integer idStart, Integer idEnd);
}
