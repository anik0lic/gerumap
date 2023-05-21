package dsw.gerumap.app.message;

import dsw.gerumap.app.observer.Subscriber;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MessageGeneratorImplementation implements MessageGenerator {
    private List<Subscriber> subscribers;
    private Message message;

    public MessageGeneratorImplementation(){
        this.subscribers = new ArrayList<>();
    }

    @SneakyThrows
    @Override
    public void generateMessage(EventType type) {
        notify(createMessage(type));
    }

    private Message createMessage(EventType type) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        switch (type) {
            case CANNOT_DELETE_PROJECT_EXPLORER:
                return new Message("Cannot delete My Project Explorer", EventType.CANNOT_DELETE_PROJECT_EXPLORER, timestamp);
            case CANNOT_ADD_CHILD_TO_LEAF:
                return new Message("Cannot add child.", EventType.CANNOT_ADD_CHILD_TO_LEAF, timestamp);
            case NODE_NOT_SELECTED:
                return new Message("Node not selected.", EventType.NODE_NOT_SELECTED, timestamp);
            case PROJECT_NOT_SELECTED:
                return new Message("You can only set the author for Projects.", EventType.PROJECT_NOT_SELECTED, timestamp);
            case MUST_INSERT_NAME:
                return new Message("Must insert name", EventType.MUST_INSERT_NAME, timestamp);
            case MUST_SET_AUTHOR:
                return new Message("Please set author for this project", EventType.MUST_SET_AUTHOR, timestamp);
            case BLANK_NAME:
                return new Message("Name cannot be blank", EventType.BLANK_NAME, timestamp);
            case MUST_INSERT_TEXT:
                return new Message("Must insert text", EventType.MUST_INSERT_TEXT, timestamp);
            case ENTER_INTEGER:
                return new Message("Please enter an integer", EventType.ENTER_INTEGER, timestamp);
            case SELECT_ELEMENTS:
                return new Message("You must select elements", EventType.SELECT_ELEMENTS, timestamp);
            case OPEN_MIND_MAP:
                return new Message("You must open mind map", EventType.OPEN_MIND_MAP, timestamp);
            case INVALID_SIZE:
                return new Message("Please enter 1, 2 or 3 for stroke size", EventType.INVALID_SIZE, timestamp);
            case INVALID_NAME_SIZE:
                return new Message("Please enter a name that has less than 10 characters", EventType.INVALID_NAME_SIZE, timestamp);
            case OPEN_FAIL:
                return new Message("Loading file failed", EventType.OPEN_FAIL, timestamp);
            case SAVE_FAIL:
                return new Message("Saving file failed", EventType.SAVE_FAIL, timestamp);
            default:
                return null;
        }
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        if (sub == null || subscribers.contains(sub))
            return;
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        if (sub == null || !subscribers.contains(sub))
            return;
        subscribers.remove(sub);
    }

    @Override
    public void notify(Object state) throws IOException {
        if (state == null || subscribers.isEmpty()) {
            System.out.println(subscribers.size());
            return;
        }

        for (Subscriber subscriber:subscribers)
            subscriber.update(state);
    }
}

