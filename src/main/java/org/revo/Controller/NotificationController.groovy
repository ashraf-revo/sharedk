package org.revo.Controller

import com.fasterxml.jackson.annotation.JsonView
import org.revo.Service.NotificationService
import org.revo.Util.View
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by revo on 5/5/16.
 */
@Controller
@RequestMapping(value = "api/notification")
class NotificationController {
    @Autowired
    NotificationService notificationService

    @JsonView(View.NotificationAndUser.class)
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity Notification(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity.ok(notificationService.notifications(id, 10))
    }
}