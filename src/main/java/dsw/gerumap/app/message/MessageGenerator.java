package dsw.gerumap.app.message;

import dsw.gerumap.app.message.EventType;
import dsw.gerumap.app.observer.Publisher;

import java.io.IOException;

public interface MessageGenerator extends Publisher {
    void generateMessage(EventType messageType) throws IOException;
}
