package com.noticeboard.Controller;

import com.noticeboard.Dao.CommentRepository;
import com.noticeboard.Dao.TopicRepository;
import com.noticeboard.Entity.Comment;
import com.noticeboard.Entity.Result;
import com.noticeboard.Entity.Topic;
import com.noticeboard.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/topic")
public class TopicController {

    @Bean
    public TopicService topicService(){
        return new TopicService();
    }
    @Autowired
    TopicService topicService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TopicRepository topicRepository;

    @RequestMapping("/main")
    public String main(){
        return "topic/main";
    }
    @RequestMapping("/")
    public String getViewByTopicId(@RequestParam("id") int topicId,
                                   Model model,
                                   HttpServletResponse response){
        Topic topic = topicRepository.findById(topicId);
        if (topic!=null){
            Cookie cookie = new Cookie("topicId",String.valueOf(topicId));
            response.addCookie(cookie);
            model.addAttribute("topic",topic);
        }
        return "comment/main";
    }

    @RequestMapping("/topics")
    @ResponseBody
    public Result main(@RequestParam("start") int start,
                       @RequestParam("end") int end){
        List<Topic> topics = topicService.getTopics(start, end);
        Result result = new Result();
        if (topics!=null && !topics.isEmpty()){
            result.setCode(Result.ResultCode.OK);
            result.setMsg(String.format("查询成功！%d-%d", start,end));
            result.setData(topics);
        }else{
            result.setMsg("查询失败，主题为空");
        }
        return result;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result addTopic(@RequestParam("topic_string") String topic_string){
        Topic topic = new Topic();
        Result result = new Result();
        try{
            topic.setTopic(topic_string);
            topicRepository.save(topic);
            result.setCode(Result.ResultCode.OK);
            result.setMsg("添加成功");
            result.setData(topic);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteTopic(@RequestParam("topic_id") int id){
        Result result = new Result();
        Topic topic = topicRepository.findById(id);
        if (topic==null)
            result.setMsg("主题不存在");
        else {
            List<Comment> comments = commentRepository.findBySrcTopic_Id(topic.getId());
            for (Comment comment : comments)
                commentRepository.delete(comment);
            topicRepository.delete(topic);
            result.setCode(Result.ResultCode.OK);
            result.setMsg("删除成功");
            result.setData(topic);
        }
        return result;
    }
}
