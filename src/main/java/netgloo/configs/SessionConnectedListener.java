package netgloo.configs;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import netgloo.Notification;
import netgloo.controllers.MainController;
import netgloo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class SessionConnectedListener extends SessionsListener implements ApplicationListener<SessionConnectedEvent> {
    @Autowired
    NotificationService notificationService;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {

        users.add(event.getUser().getName());
    }

}

@Component
class SessionDisconnectListener extends SessionsListener implements ApplicationListener<SessionDisconnectEvent> {

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        users.remove(event.getUser().getName());
    }
}

@Component
class SessionSubscribeListener extends SessionsListener implements ApplicationListener<SessionSubscribeEvent> {
    @Autowired
    NotificationService notificationService;

    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        users.add(event.getUser().getName());
        notificationService.update(event.getUser().getName());
    }
}

@Component
class SessionUnsubscribeListener extends SessionsListener implements ApplicationListener<SessionUnsubscribeEvent> {

    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        users.remove(event.getUser().getName());
    }
}

