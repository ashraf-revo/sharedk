package org.revo.Service.Impl

import com.mongodb.BasicDBObject
import com.mongodb.DBCursor
import org.bson.types.ObjectId
import org.revo.Domain.Image
import org.revo.Domain.Notification
import org.revo.Domain.User
import org.revo.Repository.NotificationRepository
import org.revo.Repository.UserRepository
import org.revo.Service.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.stereotype.Service

/**
 * Created by revo on 5/17/16.
 */
@Service
class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository
    @Autowired
    MongoOperations mongoOperations
    @Autowired
    UserRepository userRepository

    @Override
    void Save(Notification notification) {
        notificationRepository.save(notification)
    }

    @Override
    List<Notification> notifications(String lastId, int count) {
        String lt = '$lt'
        if (!lastId) {
            Optional<Notification> desc = notificationRepository.findTopByOrderByIdDesc()
            if (desc.present) {
                lastId = desc.get().id
                lt = '$lte'
            } else return []
        }
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new BasicDBObject(lt, new ObjectId(lastId)));
        DBCursor cursor = mongoOperations.getCollection("notification").find(whereQuery);
        cursor.sort(new BasicDBObject("_id", -1))
        List<Notification> notifications = []
        cursor.limit(count)
        cursor.forEach {
            notifications << new Notification(id: it["_id"], createdDate: it["createdDate"] as Date, content: it["content"], user: new User(id: it["user"]['id']))
        }
        List<User> users = userRepository.findAll(notifications*.user.id) as List<User>
        notifications.collect { notification ->
            notification.user = users.find {
                it.id == notification.user.id
            }
            notification
        }
    }
}
