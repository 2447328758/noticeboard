package com.noticeboard.Service;

import com.noticeboard.Dao.TopicRepository;
import com.noticeboard.Entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

public class TopicService {
    @Autowired
    TopicRepository topicRepository;

    //通过id范围获取Topic
    public List<Topic> getTopics(int start, int end){
        return topicRepository.findByIdBetween(start,end);
    }
}
