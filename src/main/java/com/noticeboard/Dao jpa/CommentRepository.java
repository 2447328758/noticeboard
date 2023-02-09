package com.noticeboard.Dao;

import com.noticeboard.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findBySrcTopic_Id(Integer id);

    List<Comment> findBySrcTopic_IdAndReplyTo_Id(Integer id, Integer id1);

    List<Comment> findBySrcTopic_IdAndReplyToNull(Integer id);

}
