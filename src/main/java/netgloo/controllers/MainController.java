package netgloo.controllers;

import netgloo.Notification;
import netgloo.services.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/notifications")
    public String notifications() {
        return "notifications";
    }

    @RequestMapping(value = "/action", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createAndSendNotification() {

        notificationService.notify(
                new Notification("-- A NOTIFICATION --", "UserC", "UserA"),true);
        notificationService.notify(
                new Notification("-- A NOTIFICATION --", "UserC", "UserB"),true);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}






