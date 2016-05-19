package org.revo.Event.Listener

import org.revo.Domain.User
import org.springframework.data.mongodb.core.mapping.event.*
import org.springframework.stereotype.Component

/**
 * Created by revo on 4/24/16.
 */
@Component
class UserListener extends AbstractMongoEventListener<User> {
    @Override
    void onBeforeConvert(BeforeConvertEvent<User> event) {
    }

    @Override
    void onBeforeSave(BeforeSaveEvent<User> event) {
    }

    @Override
    void onAfterSave(AfterSaveEvent<User> event) {
    }

    @Override
    void onAfterLoad(AfterLoadEvent<User> event) {
    }

    @Override
    void onAfterConvert(AfterConvertEvent<User> event) {
    }

    @Override
    void onAfterDelete(AfterDeleteEvent<User> event) {
    }

    @Override
    void onBeforeDelete(BeforeDeleteEvent<User> event) {
    }
}