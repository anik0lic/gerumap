package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.maprepository.composite.MapNode;

public abstract class NodeFactory extends MapNode {
    public abstract MapNode createNode(MapNode node);
    public MapNode getNode(MapNode parent){
        MapNode n = createNode(parent);
        n.setParent(parent);
        return n;
    }
}
