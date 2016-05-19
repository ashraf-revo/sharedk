package org.revo.Service;

import org.revo.Domain.Image;
import org.revo.Domain.Notification;
import org.revo.Domain.User;

import java.util.List;

/**
 * Created by revo on 5/15/16.
 */
public interface MessageService {
    void SendImage(User user, Image image);

    void BroadcastImage(Image image);

    void BroadcastImage(List<User> users, Image image);


    void SendNotification(User user, Notification notification);

    void BroadcastNotification(Notification notification);

    void BroadcastNotification(List<User> users, Notification notification);
}
