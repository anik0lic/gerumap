package dsw.gerumap.app.state.model;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.MoveCommand;
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

public class MoveState extends State {
    int flag = 0;
    int pocetnoX, pocetnoY;
    int xPrvo, yPrvo;
    private List<ElementPainter> painteri = new ArrayList<>();
    @Override
    public void misKliknut(int x, int y, MapView map) {
        Point point = new Point(x, y);
        for(ElementPainter p : map.getMindMap().getPainterList()){
            if(p.getElement() instanceof Topic){
                Topic t = (Topic) p.getElement();
                if(map.getSelectionModel().getSelected().contains(t)){
                    if(p.elementAt(point)){
                        painteri.clear();
                        pocetnoX = x;
                        pocetnoY = y;
                        xPrvo = x;
                        yPrvo = y;
                        flag = 1;
                        break;
                    }else{
                        flag = 0;
                    }
                }
            }
        }
    }
    @Override
    public void misOtpusten(int x, int y, MapView map) throws IOException {
        if(flag == 1) {
            AbstractCommand command = new MoveCommand(map, painteri, x, y, pocetnoX, pocetnoY);
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
        }
    }


    @Override
    public void misPovucen(int x, int y, MapView map) throws IOException {
        if (flag == 1) {
            for(ElementPainter p : map.getMindMap().getPainterList()){
                if(p.getElement() instanceof Topic){
                    Topic t = (Topic) p.getElement();
                    if (map.getSelectionModel().getSelected().contains(t)) {
                        if(!painteri.contains(p))
                            painteri.add(p);
                        t.setX(t.getX() - (xPrvo - x));
                        t.setY(t.getY() - (yPrvo - y));
                        for(ConnectionPainter painter : t.getConnectionList()){
                            Connection con = (Connection) painter.getElement();
                            if(con.getFirstTopic().equals(t)){
                                Point pos1 = new Point(painter.getPos1().x - (xPrvo - x), painter.getPos1().y - (yPrvo - y));
                                painter.setPos1(pos1);
                            }else{
                                Point pos2 = new Point(painter.getPos2().x - (xPrvo - x), painter.getPos2().y - (yPrvo - y));
                                painter.setPos2(pos2);
                            }
                        }
                    }
                }
            }
            xPrvo -= xPrvo - x;
            yPrvo -= yPrvo - y;
        }
    }

}
