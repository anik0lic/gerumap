package dsw.gerumap.app.maprepository.factory;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProjectFactory extends NodeFactory {
    private static int num = 0;
    @Override
    public MapNode createNode(MapNode node) {
        return new Project("Project " + num++, node);
    }
}
