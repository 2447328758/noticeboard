package com.noticeboard.Entity;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Column(nullable = false)
    private String comment;
    //源主题
    @ManyToOne
    @JoinColumn(name = "src_topic_id",nullable = false)
    private Topic srcTopic;
    private Time publishTime;

    @ManyToOne
    @JoinColumn(name = "reply_to_id")
    private Comment replyTo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Comment replyTo) {
        this.replyTo = replyTo;
    }


    public Topic getSrcTopic() {
        return srcTopic;
    }

    public void setSrcTopic(Topic srcTopic) {
        this.srcTopic = srcTopic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Time getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Time publishTime) {
        this.publishTime = publishTime;
    }
}
