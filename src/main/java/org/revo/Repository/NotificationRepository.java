package org.revo.Repository;

import org.revo.Domain.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by revo on 5/17/16.
 */
public interface NotificationRepository extends CrudRepository<Notification, String> {
    Optional<Notification> findTopByOrderByIdDesc();
}
