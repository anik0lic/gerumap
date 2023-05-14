package dsw.gerumap.app.maprepository;

import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import lombok.Getter;

@Getter
public class MapRepositoryImplementation implements MapRepository {
    private final ProjectExplorer projectExplorer;

    public MapRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("Project Explorer");
    }
}
