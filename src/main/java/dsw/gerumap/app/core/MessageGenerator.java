package dsw.gerumap.app.core;

import dsw.gerumap.app.gui.swing.message.EventType;
import dsw.gerumap.app.observer.Publisher;

import java.io.IOException;

public interface MessageGenerator extends Publisher {

    void generateMessage(EventType messageType) throws IOException;

}
