package dsw.gerumap.app.gui.swing.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private String content;
    private EventType type;

    public Message(String content, EventType type) {
        this.content = content;
        this.type = type;
    }
    @Override
    public String toString() {
        return content;
    }
}
