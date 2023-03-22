package dsw.gerumap.app.gui.swing.maprepository.factory.utils;

import dsw.gerumap.app.gui.swing.maprepository.NodeFactory;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.maprepository.factory.MindMapFactory;
import dsw.gerumap.app.gui.swing.maprepository.factory.ProjectFactory;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Project;
import dsw.gerumap.app.gui.swing.maprepository.implementation.ProjectExplorer;

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
