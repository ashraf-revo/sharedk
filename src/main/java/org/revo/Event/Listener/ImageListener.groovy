package org.revo.Event.Listener

import org.revo.Domain.Image
import org.revo.Domain.Notification
import org.revo.Service.MessageService
import org.revo.Service.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.mapping.event.*
import org.springframework.stereotype.Component

/**
 * Created by revo on 5/5/16.
 */
@Component
class ImageListener extends AbstractMongoEventListener<Image> {
    @Autowired
    NotificationService notificationService
    @Autowired
    MessageService messageService

    @Override
    void onBeforeConvert(BeforeConvertEvent<Image> event) {
    }

    @Override
    void onBeforeSave(BeforeSaveEvent<Image> event) {
    }

    @Override
    void onAfterSave(AfterSaveEvent<Image> event) {
        messageService.BroadcastImage(event.source)
        notificationService.Save(Notification.from(event.source))
    }

    @Override
    void onAfterLoad(AfterLoadEvent<Image> event) {
    }

    @Override
    void onAfterConvert(AfterConvertEvent<Image> event) {
    }

    @Override
    void onAfterDelete(AfterDeleteEvent<Image> event) {
    }

    @Override
    void onBeforeDelete(BeforeDeleteEvent<Image> event) {
    }
}
