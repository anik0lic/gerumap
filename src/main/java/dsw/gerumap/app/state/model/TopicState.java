package dsw.gerumap.app.state.model;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.commands.AbstractCommand;
import dsw.gerumap.app.commands.implementation.AddTopicCommand;
import dsw.gerumap.app.view.frame.MainFrame;
import dsw.gerumap.app.workspace.view.MapView;
import dsw.gerumap.app.workspace.elements.Topic;
import dsw.gerumap.app.workspace.elements.painters.ElementPainter;
import dsw.gerumap.app.workspace.elements.painters.TopicPainter;
import dsw.gerumap.app.state.State;
import dsw.gerumap.app.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TopicState extends State {
    @Override
    public void mousePressed(Point position, MindMap map, ProjectView projectView) throws IOException {
        MapView mapView = (MapView) projectView.getTabbedPane().getSelectedComponent();
        int flag = 0;
        for(ElementPainter p : map.getPainterList()){
            if(p.elementAt(position)){
                flag = 1;
                break;
            }
        }
        if(flag == 1){
            position.x += 20;
            position.y += 20;
        }
        String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Input text");
        if(name == null)
            return;
        if(name.isEmpty()) {
            name = "Topic " + map.numberOfTopics();
        }

        Topic topic = new Topic(name, map, Color.BLACK, 2, position.x, position.y);
        topic.setColor2(Color.BLACK);
        TopicPainter tp = new TopicPainter(topic);

        topic.addSubscriber(mapView);

        AbstractCommand command = new AddTopicCommand(mapView, tp);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
    }

    @Override
    public void mouseReleased(Point position, MindMap map, ProjectView projectView) {

    }

    @Override
    public void mouseDragged(Point position, MindMap map, ProjectView projectView) {

    }


}
