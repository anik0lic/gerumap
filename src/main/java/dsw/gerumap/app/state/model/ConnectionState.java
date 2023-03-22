package dsw.gerumap.app.state.model;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.AddConnectionCommand;
import dsw.gerumap.app.gui.swing.workspace.MapView;
import dsw.gerumap.app.gui.swing.workspace.panel.Connection;
import dsw.gerumap.app.gui.swing.workspace.panel.Topic;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ElementPainter;
import dsw.gerumap.app.state.State;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionState extends State {
    Topic t1;
    Topic t2;
    private ConnectionPainter connectionPainter;
    private Connection connection;
    List<ElementPainter> nova = new ArrayList<>();
    private Point pos1;
    private Point pos2;

    @Override
    public void misKliknut(int x, int y, MapView map){

        pos1 = new Point(x, y);
        pos2 = new Point(x, y);
        t1 = null;
        t2 = null;
        nova.clear();

        for(ElementPainter p : map.getMindMap().getPainterList()){
            if(p.elementAt(pos1)){
                t1 = (Topic) p.getElement();
                t2 = (Topic) p.getElement();

                connection = new Connection("Link + " + map.getMindMap().getChildren().size(), t1.getParent(), Color.BLACK, 2, t1, t2);

                connectionPainter = new ConnectionPainter(pos1, pos2,  connection);
                nova.add(connectionPainter);
            }
        }

        for(ElementPainter n : nova){
            map.getMindMap().getPainterList().add(n);
        }
    }

    @Override
    public void misOtpusten(int x, int y, MapView map) throws IOException {
        if(t1 == null || t2 == null){
            return;
        }
        pos2.setLocation(x, y);

        for(ElementPainter p : map.getMindMap().getPainterList()){
            if(p.elementAt(pos2) && p.getElement() instanceof Topic){
                connectionPainter.setPos2(pos2);
                t2 = (Topic) p.getElement();
                connection.setSecondTopic(t2);
                break;
            }
        }

        if(t1.equals(t2)){
            map.getMindMap().getPainterList().remove(connectionPainter);
            map.update(this);
            return;
        }

        for(ConnectionPainter painter : t1.getConnectionList()){
            Connection c1 = (Connection) painter.getElement();
            if(c1.equals(connection)){
                map.getMindMap().getPainterList().remove(connectionPainter);
                map.update(this);
                return;
            }
        }

        for(ConnectionPainter painter : t2.getConnectionList()){
            Connection c1 = (Connection) painter.getElement();
            if(c1.equals(connection)){
                map.getMindMap().getPainterList().remove(connectionPainter);
                map.update(this);
                return;
            }
        }

        AbstractCommand command = new AddConnectionCommand(t1, t2, map, connectionPainter, connection);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
    }

    @Override
    public void misPovucen(int x, int y, MapView map) {
        pos2.setLocation(x, y);
        map.update(connectionPainter);
    }
}
