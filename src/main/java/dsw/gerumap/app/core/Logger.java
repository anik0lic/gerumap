package dsw.gerumap.app.core;

import dsw.gerumap.app.message.Message;
import dsw.gerumap.app.observer.Subscriber;

import java.io.IOException;

public interface Logger extends Subscriber {
    void log(Message message) throws IOException;

    @Override
    default void update(Object object) throws IOException {
        Message message = (Message)object;
        log(message);
    }
}
