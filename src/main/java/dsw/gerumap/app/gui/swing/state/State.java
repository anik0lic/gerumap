package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.workspace.view.MapView;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.*;
import java.io.IOException;

public abstract class State {
    public abstract void mousePressed(Point pos, MindMap map, ProjectView projectView) throws IOException;

    public abstract void mouseReleased(Point pos, MindMap map, ProjectView projectView) throws IOException;

    public abstract void mouseDragged(Point pos, MindMap map, ProjectView projectView) throws IOException;
}
