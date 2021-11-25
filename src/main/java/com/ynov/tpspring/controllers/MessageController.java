package com.ynov.tpspring.controllers;

import com.ynov.tpspring.dto.CreateMessageDTO;
import com.ynov.tpspring.entities.Message;
import com.ynov.tpspring.entities.User;
import com.ynov.tpspring.services.AuthService;
import com.ynov.tpspring.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/{projectId}", method = RequestMethod.POST)
    public Message createMessage(@PathVariable("projectId") Long projectId,
                                 @RequestBody CreateMessageDTO message,
                                 Principal principal) {
        User user = authService.whoami(principal);
        Message newMessage = new Message();
        newMessage.setContent(message.getContent());
        return messageService.createMessage(projectId, message, user);
    }


    @RequestMapping(value = "/{messageId}", method = RequestMethod.GET)
    public Message getMessages(@PathVariable("messageId") Long messageId) {
        return messageService.getMessage(messageId);
    }
}
