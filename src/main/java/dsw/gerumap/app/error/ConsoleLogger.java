package dsw.gerumap.app.error;

import dsw.gerumap.app.core.Logger;
import dsw.gerumap.app.message.Message;
import dsw.gerumap.app.message.EventType;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class ConsoleLogger implements Logger {

    @Override
    public void log(Message message) {
        EventType et = message.getType();

        Date date = new Date();
        System.out.println("[" + et.toString() + "] [" + date +"] " + message.getContent());
    }

}
