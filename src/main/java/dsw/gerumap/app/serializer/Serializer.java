package dsw.gerumap.app.serializer;

import dsw.gerumap.app.maprepository.implementation.Project;

import java.io.File;
import java.io.IOException;

public interface Serializer {

//    Project loadProject(File file);
//    void saveProject(Project node);
    void saveProject(File file);
    void loadProject(File file) throws IOException;


}
