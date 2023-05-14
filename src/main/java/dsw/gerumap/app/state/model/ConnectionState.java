package dsw.gerumap.app.state.model;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.commands.AbstractCommand;
import dsw.gerumap.app.commands.implementation.AddConnectionCommand;
import dsw.gerumap.app.workspace.elements.painters.TopicPainter;
import dsw.gerumap.app.workspace.view.MapView;
import dsw.gerumap.app.workspace.elements.Connection;
import dsw.gerumap.app.workspace.elements.Topic;
import dsw.gerumap.app.workspace.elements.painters.ConnectionPainter;
import dsw.gerumap.app.workspace.elements.painters.ElementPainter;
import dsw.gerumap.app.state.State;
import dsw.gerumap.app.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionState extends State {
    private Topic t1;
    private Topic t2;
    private MapView mapView;
    private ConnectionPainter connectionPainter;
    private Connection connection;
    private List<ElementPainter> nova = new ArrayList<>();

    @Override
    public void mousePressed(Point position, MindMap map, ProjectView projectView) throws IOException {
        mapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
        if(!mapView.getSelectionModel().getSelected().isEmpty())
            mapView.getSelectionModel().clearList();
        nova.clear();

        for(ElementPainter p : map.getPainterList()){
            if(p.elementAt(position) && p instanceof TopicPainter){
                t1 = (Topic) p.getElement();
                connection = new Connection("Link + " + map.getChildren().size(), t1.getParent(), Color.BLACK, 2, t1);
                connectionPainter = new ConnectionPainter(connection);
                nova.add(connectionPainter);
            }
        }

        for(ElementPainter n : nova){
            map.getPainterList().add(n);
        }
    }

    @Override
    public void mouseReleased(Point position, MindMap map, ProjectView projectView) throws IOException {
        map.getPainterList().remove(connectionPainter);
        mapView.update(this);

        for(ElementPainter p : map.getPainterList()){
            if(p.elementAt(position) && p instanceof TopicPainter){
                t2 = (Topic) p.getElement();
                connection.setSecondTopic(t2);
                AbstractCommand command = new AddConnectionCommand(t1, t2, map, projectView);
                ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
                connectionPainter = null;
                return;
            }
        }
    }

    @Override
    public void mouseDragged(Point position, MindMap map, ProjectView projectView) {
        connection.setXS(position.x);
        connection.setYS(position.y);
        mapView.update(this);
    }
}
