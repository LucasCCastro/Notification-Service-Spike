package netgloo.configs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SessionsListener {

    protected List<String> users = Collections.synchronizedList(new LinkedList<String>());

    public List<String> getUsers() {
        return users;
    }
}
