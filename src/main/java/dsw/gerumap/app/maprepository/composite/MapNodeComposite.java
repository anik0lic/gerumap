package dsw.gerumap.app.maprepository.composite;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.workspace.elements.Connection;
import dsw.gerumap.app.workspace.elements.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Connection.class, name = "connection"),
        @JsonSubTypes.Type(value = MindMap.class, name = "mindMap"),
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = ProjectExplorer.class, name = "projectExplorer"),
        @JsonSubTypes.Type(value = Topic.class, name = "topic")
})
public abstract class MapNodeComposite extends MapNode{
    protected List<MapNode> children;

    public MapNodeComposite(String name, MapNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(MapNode child) throws IOException;
    public abstract void removeChild(MapNode child) throws IOException;

    public void setChildrenParents() throws IOException {

        for(int i = 0; i < this.children.size(); i++){

            this.children.get(i).setParent(this);

            if(this.children.get(i) instanceof MapNodeComposite) {

                ((MapNodeComposite) this.children.get(i)).setChildrenParents();
                notify(this);

            }
        }
    }
}
