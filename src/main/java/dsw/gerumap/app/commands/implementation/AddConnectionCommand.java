package dsw.gerumap.app.commands.implementation;

import dsw.gerumap.app.commands.AbstractCommand;
import dsw.gerumap.app.workspace.view.MapView;
import dsw.gerumap.app.workspace.elements.Connection;
import dsw.gerumap.app.workspace.elements.Topic;
import dsw.gerumap.app.workspace.elements.painters.ConnectionPainter;
import dsw.gerumap.app.workspace.elements.painters.ElementPainter;
import dsw.gerumap.app.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.*;
import java.io.IOException;

public class AddConnectionCommand extends AbstractCommand {

    private Topic t1;
    private Topic t2;
    private MindMap map;
    private ElementPainter painter;
    private ProjectView projectView;
    private Connection connection;

    public AddConnectionCommand(Topic t1, Topic t2, MindMap map, ProjectView projectView) {
        this.t1 = t1;
        this.t2 = t2;
        this.map = map;
        this.projectView = projectView;
    }

    @Override
    public void doCommand() throws IOException {
        System.out.println("do");
        System.out.println(painter);
        if(painter == null){
            String name = t1.getName() + "-" + t2.getName();
            connection = new Connection(name, map, Color.BLACK, 2, t1);
            connection.setSecondTopic(t2);
            painter = new ConnectionPainter(connection);

            if(map.getPainterList().contains(painter)){
                ((MapView)projectView.getTabbedPane().getSelectedComponent()).getCommandManager().getCommands().remove(this);
                return;
            }

            if(connection.getFirstTopic().equals(connection.getSecondTopic())){
                ((MapView)projectView.getTabbedPane().getSelectedComponent()).getCommandManager().getCommands().remove(this);
                return;
            }
        }
        t1.getConnectionList().add((ConnectionPainter) painter);
        t2.getConnectionList().add((ConnectionPainter) painter);
        map.addChild(connection);
        map.getPainterList().add(painter);
    }

    @Override
    public void undoCommand() throws IOException {
        t1.getConnectionList().remove((ConnectionPainter) painter);
        t2.getConnectionList().remove((ConnectionPainter) painter);
        map.removeChild(connection);
        map.getPainterList().remove(painter);
    }
}
