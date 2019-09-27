package netgloo.services;

import netgloo.Notification;
import netgloo.NotificationRepository;
import netgloo.configs.SessionsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Qualifier("sessionConnectedListener")
    @Autowired
    private SessionsListener sessionsListener;

    public void notify(Notification notification, boolean save) {
        List<String> userList = sessionsListener.getUsers();

        if (userList.contains(notification.getReceivers())) {
            messagingTemplate.convertAndSendToUser(
                    notification.getReceivers(),
                    "/queue/notify",
                    notification
            );
        }
        if (save)
            notificationRepository.save(notification);
    }

    public void update(String name) {
        Iterable<Notification> listNotification = notificationRepository.getByName(name);
        Iterator<Notification> it = listNotification.iterator();

        while (it.hasNext()) {
            Notification auxNot = it.next();
          notify(auxNot, false);
        }
    }

}
