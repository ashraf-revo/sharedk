package org.revo.Service

import org.revo.Domain.Notification

/**
 * Created by revo on 5/17/16.
 */
interface NotificationService {
    void Save(Notification notification)

    List<Notification> notifications(String lastId, int count)
}