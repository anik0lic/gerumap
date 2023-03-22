package dsw.gerumap.app.gui.swing.commands.implementation;

import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.workspace.MapView;
import dsw.gerumap.app.gui.swing.workspace.panel.Connection;
import dsw.gerumap.app.gui.swing.workspace.panel.Topic;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ElementPainter;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MoveCommand extends AbstractCommand {

    private MapView map;
    private List<ElementPainter> painteri;
    private int x;
    private int y;
    private int pocetnoX;
    private int pocetnoY;
    private int flag = 0;

    public MoveCommand(MapView map, List<ElementPainter> painteri, int x, int y, int pocetnoX, int pocetnoY) {
        this.map = map;
        this.painteri = painteri;
        this.x = x;
        this.y = y;
        this.pocetnoX = pocetnoX;
        this.pocetnoY = pocetnoY;
    }

    @Override
    public void doCommand() throws IOException {
        System.out.println("aaaaaa");
        if (flag > 0) {
            for (ElementPainter p : map.getMindMap().getPainterList()) {
                if (p.getElement() instanceof Topic) {
                    Topic t = (Topic) p.getElement();
                    if (painteri.contains(p)) {
                        t.setX(t.getX() - (pocetnoX - x));
                        t.setY(t.getY() - (pocetnoY - y));
                        for (ConnectionPainter painter : t.getConnectionList()) {
                            Connection con = (Connection) painter.getElement();
                            if (con.getFirstTopic().equals(t)) {
                                Point pos1 = new Point(painter.getPos1().x - (pocetnoX - x), painter.getPos1().y - (pocetnoY - y));
                                painter.setPos1(pos1);
                            } else {
                                Point pos2 = new Point(painter.getPos2().x - (pocetnoX - x), painter.getPos2().y - (pocetnoY - y));
                                painter.setPos2(pos2);
                            }
                        }
                    }
                }
            }
        }
        flag++;
    }

    @Override
    public void undoCommand() throws IOException {
        for (ElementPainter p : map.getMindMap().getPainterList()) {
            if (p.getElement() instanceof Topic) {
                Topic t = (Topic) p.getElement();
                if (painteri.contains(p)) {
                    t.setX(t.getX() + (pocetnoX - x));
                    t.setY(t.getY() + (pocetnoY - y));
                    for (ConnectionPainter painter : t.getConnectionList()) {
                        Connection con = (Connection) painter.getElement();
                        if (con.getFirstTopic().equals(t)) {
                            Point pos1 = new Point(painter.getPos1().x + (pocetnoX - x), painter.getPos1().y + (pocetnoY - y));
                            painter.setPos1(pos1);
                        } else {
                            Point pos2 = new Point(painter.getPos2().x + (pocetnoX - x), painter.getPos2().y + (pocetnoY - y));
                            painter.setPos2(pos2);
                        }
                    }
                }
            }
        }
    }
}
