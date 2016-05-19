package org.revo.Domain

import com.fasterxml.jackson.annotation.JsonView
import groovy.transform.Canonical
import org.revo.Util.View
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

import javax.persistence.Entity

/**
 * Created by revo on 5/17/16.
 */
@Entity
@Canonical
@Document
class Notification {
    @Id
    @JsonView(View.Notification.class)
    String id
    @JsonView(View.Notification.class)
    NotificationType notificationType = NotificationType.IMAGE
    @CreatedDate
    @JsonView(View.Notification.class)
    Date createdDate
    @DBRef
    @CreatedBy
    @JsonView(View.NotificationUser.class)
    User user
    @JsonView(View.Notification.class)
    String content

    static Notification from(Image image) {
        new Notification(content: image.id)
    }
}