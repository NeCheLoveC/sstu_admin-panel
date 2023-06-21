package com.example.adminpanelbot.controllers;
import com.example.adminpanelbot.model.Message;
import com.example.adminpanelbot.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController
{
    @Autowired
    private MessageService messageService;
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping
    public String showFormAdding(Model model)
    {
        List<Message> messages = reverseList(messageService.getLastFiveMessages());
        model.addAttribute("message", new Message());
        model.addAttribute("list_messages", messages);
        return "gram";
    }

    private List<Message> reverseList(List<Message> messages)
    {
        for(int i = 0;i < (messages.size() / 2);i++)
        {
            int index = (messages.size() - 1 - i);
            Message buf = messages.get(i);
            messages.set(i,messages.get(index));
            messages.set(index,buf);
        }
        return messages;
    }

    @PostMapping("/add")
    public RedirectView addMessage(@RequestParam(name = "text") String text)
    {
        this.messageService.save(new Message(text));
        redisTemplate.convertAndSend("message:channel",text);
        return new RedirectView("/message");
    }
}
