package dsw.gerumap.app.gui.swing.maprepository;

import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;

public abstract class NodeFactory extends MapNode {
    public MapNode getNode(MapNode parent){
        MapNode n = createNode(parent);
        n.setParent(parent);
        return n;
    }
    public abstract MapNode createNode(MapNode node);

}
