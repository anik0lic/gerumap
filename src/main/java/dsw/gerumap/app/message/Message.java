package dsw.gerumap.app.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private String content;
    private EventType type;
    private Timestamp timestamp;

    public Message(String content, EventType type, Timestamp timestamp) {
        this.content = content;
        this.type = type;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return content;
    }
}
