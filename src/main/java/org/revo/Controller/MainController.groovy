package org.revo.Controller

import org.revo.Domain.User
import org.revo.Model.Message
import org.revo.Repository.ImageRepository
import org.revo.Repository.UserRepository
import org.revo.Service.SecurityService
import org.revo.Service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.user.SimpUserRegistry
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import java.security.Principal

/**
 * Created by revo on 4/24/16.
 */

@Controller
@RequestMapping("/api")
class MainController {
    @Autowired
    UserRepository userRepository
    @Autowired
    SimpUserRegistry simpUserRegistry
    @Autowired
    SecurityService securityService
    @Autowired
    UserService userService

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseEntity register(@Validated @RequestBody User user) {
        try {
            ResponseEntity.ok(userService.Save(user))
        } catch (DuplicateKeyException ignored) {
            ResponseEntity.badRequest().body("Please change your email")
        }
    }

    @RequestMapping(value = "/")
    def all() {
        ResponseEntity.ok(userRepository.findAll())
    }
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate
    @Autowired
    ImageRepository imageRepository

    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
    void message(Principal principal, Message m) {
//        simpMessagingTemplate.convertAndSend("/topic/greetings", m)
    }

    @RequestMapping(value = "/connected")
    def connected() {
        ResponseEntity.ok(simpUserRegistry.users.collect {
            ["name": it.name, "sessions": it.sessions.collect {
                ["id": it.id, "subscriptions": it.subscriptions.collect {
                    ["id": it.id, "destination": it.destination]
                }]
            }]
        })
    }
}
