package org.revo.Event

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionSubscribeEvent

/**
 * Created by revo on 4/24/16.
 */
@Component
class PresenceEventListener {
    @EventListener
    private void Connect(SessionConnectEvent event) {
    }

    @EventListener
    private void Subscribe(SessionSubscribeEvent event) {
    }
}
