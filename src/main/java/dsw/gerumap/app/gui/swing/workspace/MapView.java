package dsw.gerumap.app.gui.swing.workspace;

import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Element;
import dsw.gerumap.app.gui.swing.workspace.panel.Topic;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ElementPainter;
import dsw.gerumap.app.observer.Subscriber;
import dsw.gerumap.app.gui.swing.maprepository.implementation.MindMap;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class MapView extends JPanel implements Subscriber{
    private MindMap mindMap;
    private int index;
    private MapSelectionModel selectionModel;
//    private JScrollBar vertical;
//    private JScrollBar horizontal;
    double translateX = 0;
    double translateY = 0;
    double scalingf = 1;
    private AffineTransform transformation = new AffineTransform();

    public MapView(MindMap map, int index) {
        setLayout(new BorderLayout());
//        horizontal = new JScrollBar(0);
//        vertical = new JScrollBar(1);
//        add(horizontal, BorderLayout.SOUTH);
//        add(vertical, BorderLayout.EAST);

        this.index = index;
        selectionModel = new MapSelectionModel();

        addMouseListener(new MouseController(this));
        addMouseMotionListener(new MouseController(this));

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

    private void setUpTransformation(){
        transformation.setToScale(scalingf,scalingf);
        transformation.translate(translateX,translateY);
        repaint();
    }

    public void zoomIn(){
        scalingf *= 1.6;
        if(scalingf > 4.096) scalingf = 4.096;
        setUpTransformation();

    }
    public void zoomOut(){
        scalingf *= 0.625;
        if(scalingf < 0.390625) scalingf = 0.390625;
        setUpTransformation();
    }
}
