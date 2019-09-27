package netgloo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
@SequenceGenerator(name = "NOT_SEQ", sequenceName = "SEQ_USER", initialValue = 1, allocationSize = 1)
@Table(name = "Notification")
public class Notification {

    @Column(name = "MESSAGE",nullable = false)
    private String message;

    @NotNull
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NOT_SEQ")
    private Long id;

    @NotNull
    @Column(name = "SENDER")
    String sender;

    @NotNull
    @Column(name = "RECEIVER")
    String receivers;

    public  Notification(){

    }
    public Notification(String message, String sender, String receivers) {
        this.message = message;
        this.sender = sender;
        this.receivers = receivers;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getReceivers() {
        return receivers;
    }

    public String getContent() {
        return message;
    }

}
