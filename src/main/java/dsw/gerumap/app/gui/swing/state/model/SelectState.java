package dsw.gerumap.app.gui.swing.state.model;

import dsw.gerumap.app.gui.swing.workspace.view.MapView;
import dsw.gerumap.app.gui.swing.workspace.elements.painters.ElementPainter;
import dsw.gerumap.app.gui.swing.workspace.elements.painters.MultiSelectionPainter;
import dsw.gerumap.app.gui.swing.state.State;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectState extends State {
    private MultiSelectionPainter msp = new MultiSelectionPainter();
    private int x1, y1;
    private MapView mapView;
    private List<ElementPainter> nova = new ArrayList<>();

    @Override
    public void mousePressed(Point position, MindMap map, ProjectView projectView) throws IOException {
        mapView = (MapView) projectView.getTabbedPane().getSelectedComponent();

        if(!(mapView.getSelectionModel().getSelected().isEmpty())) {
            mapView.getSelectionModel().clearList();
        }

        nova.clear();

        for(ElementPainter p : map.getPainterList()){
            if(p.elementAt(position)){
                mapView.getSelectionModel().addElement(p);
            }
            else{
                x1 = position.x;
                y1 = position.y;
                nova.add(msp);
            }
        }
        for(ElementPainter p : nova){
            map.getPainterList().add(p);
        }
    }

    @Override
    public void mouseReleased(Point position, MindMap map, ProjectView projectView) {
        for (ElementPainter n : nova) {
            map.getPainterList().remove(n);
            msp = new MultiSelectionPainter();
        }
        mapView.update(this);
    }

    @Override
    public void mouseDragged(Point position, MindMap map, ProjectView projectView) throws IOException {
        if(!(mapView.getSelectionModel().getSelected().isEmpty())) {
            mapView.getSelectionModel().clearList();
        }

        msp.updatePoints(x1, y1, position.x, position.y);
        mapView.update(this);

        for(ElementPainter p : map.getPainterList()){
            if(p instanceof MultiSelectionPainter)
                continue;

            if(msp.getS() == null){
                map.getPainterList().remove(msp);
                return;
            }
            if(msp.getS().intersects(p.getS().getBounds().getX(), p.getS().getBounds().getY(), p.getS().getBounds().getWidth(), p.getS().getBounds().getHeight())){
                mapView.getSelectionModel().addElement(p);
            }
        }
    }
}
