package dsw.gerumap.app.gui.swing.workspace.view;

import dsw.gerumap.app.gui.swing.commands.CommandManager;
import dsw.gerumap.app.gui.swing.workspace.model.MapSelectionModel;
import dsw.gerumap.app.gui.swing.workspace.controller.MouseController;
import dsw.gerumap.app.gui.swing.workspace.elements.painters.ElementPainter;
import dsw.gerumap.app.observer.Subscriber;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

@Setter
@Getter
public class MapView extends JPanel implements Subscriber{
    private MindMap mindMap;
    private int index;
    private MapSelectionModel selectionModel;
    private CommandManager commandManager;
    private AffineTransform transformation = new AffineTransform();
    private double translateX = 0.0;
    private double translateY = 0.0;
//    private double scaling = 1.0;

    public MapView(MindMap map, int index) {
        commandManager = new CommandManager();
        setLayout(new BorderLayout());

        this.index = index;
        this.selectionModel = new MapSelectionModel();

        addMouseListener(new MouseController());
        addMouseMotionListener(new MouseController());

        setMap(map);
    }

    public void setMap(MindMap mindMap) {
        this.mindMap = mindMap;
        updateTabName();
    }

    public void updateTabName() {
        JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, this);
        if (tabbedPane == null) return;
        tabbedPane.setTitleAt(index, mindMap.getName());
    }

    @Override
    public void update(Object object) {
        if(mindMap == null){
            return;
        }

        updateTabName();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setTransform(transformation);

        for(ElementPainter p : mindMap.getPainterList()){
            p.draw(g2d);
        }
    }

    private void setUpTransformation(double scaling){
        transformation.setToIdentity();
        transformation.translate(translateX, translateY);
//        transformation.scale(scaling, scaling);
        repaint();
    }

//    public void zoomIn(){
//        double newScaling = scaling * 1.2;
//        if(newScaling >= 5) newScaling = 5;
//        this.scaling = newScaling;
//        setUpTransformation(newScaling);
//
//    }
//    public void zoomOut(){
//        double newScaling = scaling * 0.8;
//        if(newScaling <= 0.2) newScaling = 0.2;
//        this.scaling = newScaling;
//        setUpTransformation(newScaling);
//    }
//
//    public void scroll(double translateX, double translateY){
//        this.translateX += translateX;
//        this.translateY += translateY;
//        setUpTransformation(scaling);
//    }
}
