package dsw.gerumap.app.observer;

import java.io.IOException;

public interface Publisher {

    void addSubs(Subscriber subscriber);
    void removeSubs(Subscriber subscriber);
    void notify(Object notification) throws IOException;

}
