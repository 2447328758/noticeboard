package com.noticeboard.Controller;

import com.noticeboard.Dao.CommentRepository;
import com.noticeboard.Dao.TopicRepository;
import com.noticeboard.Entity.Comment;
import com.noticeboard.Entity.Result;
import com.noticeboard.Entity.Topic;
import com.noticeboard.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment_api")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TopicRepository topicRepository;

    @RequestMapping("/")
    @ResponseBody
    public Result getCommentsById(@RequestParam("cid") Integer cid,
                                  @RequestParam(value = "replyCId" ,required = false) Integer replyCId){
        Result result = new Result();
        List<Comment> comments;
        if (replyCId==null)
            comments = commentRepository.findBySrcTopic_IdAndReplyToNull(cid);
        else
            comments = commentRepository.findBySrcTopic_IdAndReplyTo_Id(cid, replyCId);
        if (!comments.isEmpty()){
            result.setCode(Result.ResultCode.OK);
            result.setMsg("通过id查找初始评论成功！");
            result.setData(comments);
        }else {
            result.setMsg("没有评论！");
        }
        return result;
    }

    @PostMapping("/submit")
    @ResponseBody
    public Result submitComment(@RequestParam("cid") Integer cid,
                                @RequestParam("comment_string") String comment_string,
                                @RequestParam(value = "replyCId" ,required = false) Integer replyCId,
                                HttpSession session){
        Result result = new Result();
        User user = (User) session.getAttribute("user");
        Topic topic = topicRepository.findById(cid);
        Comment replyTo = null;
        Comment comment = new Comment();
        if (comment_string.equals(""))
            result.setMsg("评论不能为空！");
        else if (user==null){
            result.setMsg("请先登录！");
        } else if (replyCId!=null && (replyTo=commentRepository.findOne(replyCId))==null){
            result.setMsg("回复的评论不存在！");
        } else if (topic == null){
            result.setMsg("没有选定主题！");
        } else {
            comment.setComment(comment_string);
            comment.setReplyTo(replyTo);
            comment.setSrcTopic(topic);
            comment.setUser(user);
            comment.setPublishTime(new Time(new Date().getTime()));
            commentRepository.save(comment);
            result.setCode(Result.ResultCode.OK);
            result.setData(comment);
            result.setMsg("评论成功！");
        }
        return result;
    }
}
