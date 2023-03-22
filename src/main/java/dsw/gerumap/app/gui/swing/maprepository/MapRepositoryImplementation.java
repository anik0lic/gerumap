package dsw.gerumap.app.gui.swing.maprepository;

import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNodeComposite;
import dsw.gerumap.app.gui.swing.maprepository.implementation.ProjectExplorer;

public class MapRepositoryImplementation implements MapRepository {

    private ProjectExplorer projectExplorer;

    public MapRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

//    @Override
//    public void addChild(MapNodeComposite parent, MapNode child) {
//
//    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }


}
