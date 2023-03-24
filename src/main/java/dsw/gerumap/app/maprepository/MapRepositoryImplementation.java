package dsw.gerumap.app.maprepository;

import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;

@Getter
public class MapRepositoryImplementation implements MapRepository {

    private ProjectExplorer projectExplorer;

    public MapRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

//    @Override
//    public void addChild(MapNodeComposite parent, MapNode child) {
//
//    }

}
