package dsw.gerumap.app.gui.swing.workspace;

import dsw.gerumap.app.gui.swing.maprepository.implementation.Element;
import dsw.gerumap.app.observer.Publisher;
import dsw.gerumap.app.observer.Subscriber;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapSelectionModel implements Publisher {

    private List<Element> selected;
    protected List<Subscriber> subscribers;
    private Color oldColor;

    public MapSelectionModel() {
        this.selected = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void addElement(Element el) throws IOException {
        if (el != null) {
            this.getSelected().add(el);
            oldColor = el.getColor();
            el.setColor(Color.BLUE);
            notify(this);
        }
    }

    public void clearList() throws IOException {
        for(Element el : this.getSelected()){
            el.setColor(oldColor);
        }
        getSelected().clear();
    }

    @Override
    public void addSubs(Subscriber subscriber) {
        if(subscriber == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(subscriber))
            return;
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubs(Subscriber subscriber) {
        if(subscriber == null || this.subscribers == null || !this.subscribers.contains(subscriber))
            return;
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notify(Object notification) throws IOException {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(Subscriber sub : subscribers){
            sub.update(notification);
        }
    }
}
