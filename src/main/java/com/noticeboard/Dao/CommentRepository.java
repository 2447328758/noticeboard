package com.noticeboard.Dao;

import com.noticeboard.Entity.Comment;

import java.util.List;

public class CommentRepository {
    List<Comment> findBySrcTopic_Id(Integer id);

    List<Comment> findBySrcTopic_IdAndReplyTo_Id(Integer id, Integer id1);

    List<Comment> findBySrcTopic_IdAndReplyToNull(Integer id);
}
