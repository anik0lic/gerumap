package dsw.gerumap.app.gui.swing.state.model;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.MoveCommand;
import dsw.gerumap.app.gui.swing.workspace.elements.painters.TopicPainter;
import dsw.gerumap.app.gui.swing.workspace.view.MapView;
import dsw.gerumap.app.gui.swing.workspace.elements.Connection;
import dsw.gerumap.app.gui.swing.workspace.elements.Topic;
import dsw.gerumap.app.gui.swing.workspace.elements.painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.workspace.elements.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoveState extends State {
    private int pocetnoX, pocetnoY;
    private int xDva, yDva;
    private MapView mapView;
//skrol    private int flagMove = 0;
    @Override
    public void mousePressed(Point position, MindMap map, ProjectView projectView) {
        mapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
        pocetnoX = xDva = position.x;
        pocetnoY = yDva = position.y;

//skrol        if(mapView.getSelectionModel().getSelected().isEmpty())
//            flagMove = 1;
    }
    @Override
    public void mouseReleased(Point position, MindMap map, ProjectView projectView) throws IOException {
        AbstractCommand command = new MoveCommand(mapView, position.x - pocetnoX, position.y - pocetnoY);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
    }


    @Override
    public void mouseDragged(Point position, MindMap map, ProjectView projectView) throws IOException {
//skrol        if(flagMove == 1){
//            mapView.scroll(position.x - pocetnoX, position.y - pocetnoY);
//            return;
//        }

        for(ElementPainter painter : mapView.getSelectionModel().getSelected()){
            if(painter instanceof TopicPainter){
                Topic movingTopic = (Topic) painter.getElement();
                int newX = movingTopic.getX() + position.x - xDva;
                int newY = movingTopic.getY() + position.y - yDva;
                movingTopic.setX(newX);
                movingTopic.setY(newY);
            }
        }
        xDva = position.x;
        yDva = position.y;
    }

}
