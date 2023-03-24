package dsw.gerumap.app.maprepository.factory.utils;

import dsw.gerumap.app.maprepository.factory.NodeFactory;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.factory.MindMapFactory;
import dsw.gerumap.app.maprepository.factory.ProjectFactory;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;

public class FactoryUtils {

    public static NodeFactory getNodeFactory(MapNode parent) {

        if(parent instanceof ProjectExplorer){
            return new ProjectFactory();
        }
        else if(parent instanceof Project){
            return new MindMapFactory();
        }

        return null;
    }
}
