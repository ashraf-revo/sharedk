package org.revo.Event.Listener

import org.revo.Domain.Notification
import org.revo.Service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.mapping.event.*
import org.springframework.stereotype.Component

/**
 * Created by revo on 5/17/16.
 */
@Component
class NotificationListener extends AbstractMongoEventListener<Notification> {
    @Autowired
    MessageService messageService

    @Override
    void onBeforeConvert(BeforeConvertEvent<Notification> event) {
    }

    @Override
    void onBeforeSave(BeforeSaveEvent<Notification> event) {
    }

    @Override
    void onAfterSave(AfterSaveEvent<Notification> event) {
        messageService.BroadcastNotification(event.source)
    }

    @Override
    void onAfterLoad(AfterLoadEvent<Notification> event) {
    }

    @Override
    void onAfterConvert(AfterConvertEvent<Notification> event) {
    }

    @Override
    void onAfterDelete(AfterDeleteEvent<Notification> event) {
    }

    @Override
    void onBeforeDelete(BeforeDeleteEvent<Notification> event) {
    }
}
