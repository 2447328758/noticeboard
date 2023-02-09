package com.noticeboard.Dao;

import com.noticeboard.Entity.Topic;

import java.util.List;

public class TopicRepository {
    Topic findById(Integer id);

    List<Topic> findByIdBetween(Integer idStart, Integer idEnd);
}
