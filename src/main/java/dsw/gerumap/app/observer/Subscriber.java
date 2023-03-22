package dsw.gerumap.app.observer;

import java.io.IOException;

public interface Subscriber {
    void update(Object object) throws IOException;

}
