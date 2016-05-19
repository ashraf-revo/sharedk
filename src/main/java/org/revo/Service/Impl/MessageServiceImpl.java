package org.revo.Service.Impl;

import org.revo.Domain.Image;
import org.revo.Domain.Notification;
import org.revo.Domain.User;
import org.revo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by revo on 5/15/16.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void SendImage(User user, Image image) {
        simpMessagingTemplate.convertAndSendToUser(user.getEmail(), "/topic/images", image);
    }

    @Override
    public void BroadcastImage(Image image) {
        simpMessagingTemplate.convertAndSend("/topic/images", image);
    }

    @Override
    public void BroadcastImage(List<User> users, Image image) {
        users.forEach(user -> SendImage(user, image));
    }

    @Override
    public void SendNotification(User user, Notification notification) {
        simpMessagingTemplate.convertAndSendToUser(user.getEmail(), "/topic/notifications", notification);
    }

    @Override
    public void BroadcastNotification(Notification notification) {
        simpMessagingTemplate.convertAndSend("/topic/notifications", notification);
    }

    @Override
    public void BroadcastNotification(List<User> users, Notification notification) {
        users.forEach(it -> SendNotification(it, notification));
    }
}
