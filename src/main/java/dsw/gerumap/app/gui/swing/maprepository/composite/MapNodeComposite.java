package dsw.gerumap.app.gui.swing.maprepository.composite;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class MapNodeComposite extends MapNode{
    private List<MapNode> children;

    public MapNodeComposite(String name, MapNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(MapNode child) throws IOException;
    public abstract void removeChild(MapNode child) throws IOException;
}
