package dsw.gerumap.app.maprepository.composite;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.observer.Publisher;
import dsw.gerumap.app.observer.Subscriber;
import dsw.gerumap.app.workspace.elements.Connection;
import dsw.gerumap.app.workspace.elements.Topic;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Connection.class, name = "connection"),
        @JsonSubTypes.Type(value = MindMap.class, name = "mindMap"),
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = ProjectExplorer.class, name = "projectExplorer"),
        @JsonSubTypes.Type(value = Topic.class, name = "topic")
})
public abstract class MapNode implements Publisher {
    private String name;
    private  transient List<Subscriber> subscribers;
    private transient MapNode parent;

    public MapNode(String name, MapNode parent) {
        this.name = name;
        this.parent = parent;
        subscribers = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MapNode) {
            MapNode otherObj = (MapNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }

    public void setName(String name) throws IOException {
        this.name = name;
        notify(name);
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(subscriber == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(subscriber))
            return;
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
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
