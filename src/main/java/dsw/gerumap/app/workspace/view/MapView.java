package dsw.gerumap.app.workspace.view;

import dsw.gerumap.app.commands.CommandManager;
import dsw.gerumap.app.workspace.model.MapSelectionModel;
import dsw.gerumap.app.workspace.controller.MouseController;
import dsw.gerumap.app.workspace.elements.painters.ElementPainter;
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

    public MapView(MindMap map, int index) {
        commandManager = new CommandManager();

        this.index = index;
        this.selectionModel = new MapSelectionModel();

        setSize(new Dimension((int) (3000 * Math.pow(1.25, 7)), (int) (3000 * Math.pow(1.25, 7))));
        setPreferredSize(new Dimension((int) (3000 * Math.pow(1.25, 7)), (int) (2000 * Math.pow(1.25, 7))));

        addMouseListener(new MouseController());
        addMouseMotionListener(new MouseController());

        setMap(map);
    }

    public void setMap(MindMap mindMap) {
        this.mindMap = mindMap;
        this.mindMap.addSubscriber(this);
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

        for(ElementPainter p : mindMap.getPainterList()){
            p.draw(g2d);
        }
    }
}
