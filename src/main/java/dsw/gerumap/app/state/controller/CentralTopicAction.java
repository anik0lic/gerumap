package dsw.gerumap.app.state.controller;

import dsw.gerumap.app.gui.swing.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Element;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.panel.Connection;
import dsw.gerumap.app.gui.swing.workspace.panel.Topic;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ElementPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class CentralTopicAction extends AbstractGeRuMapAction {

    public CentralTopicAction () {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/central.png"));
        putValue(NAME, "Central Topic");
        putValue(SHORT_DESCRIPTION, "Central Topic");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getSelectionModel().getSelected().size() == 1){

            for(Element central : MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getSelectionModel().getSelected()) {
                if (!(central instanceof Topic)) {
                    return;
                }


                for(ElementPainter p : MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getMindMap().getPainterList()){
                    if(!(p.getElement() instanceof Topic)) continue;
                    Topic p1 = (Topic) p.getElement();
                    p1.setNivo(-1);

                }


                try {
                    central.setStroke(5);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                int sirina = MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getWidth() / 2;
                int visina = MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getHeight() / 2;

                sirina /= 8;
                visina /= 8;
                sirina *= 8;
                visina *= 8;
                System.out.println(visina + " " + sirina);
                int pocY = visina;
                int pocX = sirina - 200;

                Topic t = (Topic) central;
                t.setNivo(0);

                try {
                    t.setX(sirina);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    t.setY(visina);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                for (ConnectionPainter painter : t.getConnectionList()) {
                    Connection con = (Connection) painter.getElement();
                    if (con.getFirstTopic().equals(t)) {
                        Point pos1 = new Point(sirina, visina);
                        painter.setPos1(pos1);
                    } else {
                        Point pos2 = new Point(sirina, visina);
                        painter.setPos2(pos2);
                    }
                }

                boolean dodajeX = false;
                boolean dodajeY = true;
                int pomoc = 0;


                int vrednostX = 200;
                int vrednostY = 150;

                if (t.getConnectionList().size() <= 4) {
                    vrednostX /= 1;
                    vrednostY /= 1;
                } else if (t.getConnectionList().size() <= 8) {
                    vrednostX /= 2;
                    vrednostY /= 2;
                } else if (t.getConnectionList().size() <= 16) {
                    vrednostX /= 4;
                    vrednostY /= 4;
                }


                int ostalePomoc = MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getHeight();
                int red=0;

                while(true){
                    int pomozi = 0;
                    for(ElementPainter p : MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getMindMap().getPainterList()){
                        if(!(p.getElement() instanceof Topic)) continue;
                        Topic p1= (Topic) p.getElement();
                        if(p1.getNivo()==red){
                            pomozi++;
                            for(ElementPainter k : MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getMindMap().getPainterList()){
                                if(!(k.getElement() instanceof Topic)) continue;
                                Topic k1 = (Topic) k.getElement();
                                for (ConnectionPainter painter : k1.getConnectionList()){
                                    Connection linkara = (Connection) painter.getElement();
                                    if((linkara.getFirstTopic().equals(p1) || linkara.getSecondTopic().equals(p1)) && k1.getNivo()==-1){
                                        k1.setNivo(red+1);
                                    }
                                }
                            }
                        }
                    }
                    if(pomozi==0) break;
                    else red ++;
                }


                for(ElementPainter p : MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getMindMap().getPainterList()){
                    if(!(p.getElement() instanceof Topic)) continue;
                    Topic p1 = (Topic) p.getElement();
                }


                for (ElementPainter p : MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getMindMap().getPainterList()) {
                    if (p.getElement() instanceof Topic && !(p.getElement().equals(t))) {
                        Topic k = (Topic) p.getElement();
                        for (ConnectionPainter painter : k.getConnectionList()) {


                            Connection con = (Connection) painter.getElement();
                            if (con.getFirstTopic().equals(t)) {


                                if (dodajeX) {
                                    pocX += pomoc * vrednostX;
                                } else pocX -= pomoc * vrednostX;

                                if (dodajeY) pocY += pomoc * vrednostY;
                                else pocY -= pomoc * vrednostY;

                                try {
                                    k.setX(pocX);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                try {
                                    k.setY(pocY);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                                for (ConnectionPainter slikar : k.getConnectionList()) {
                                    Connection linkara = (Connection) slikar.getElement();
                                    if (linkara.getFirstTopic().equals(k)) {
                                        Point pos1 = new Point(pocX, pocY);

                                        slikar.setPos1(pos1);
                                    } else {
                                        Point pos2 = new Point(pocX, pocY);
                                        slikar.setPos2(pos2);
                                    }
                                }


                                Point pos2 = new Point(pocX, pocY);
                                painter.setPos2(pos2);

                                if (pocY == t.getY()) {
                                    if (dodajeX) {
                                        dodajeX = false;
                                    } else {
                                        dodajeX = true;
                                    }
                                }
                                if (pocX == t.getX()) {
                                    if (dodajeY) {
                                        dodajeY = false;
                                    } else dodajeY = true;
                                }

                                pomoc = 1;


                            } else if (con.getSecondTopic().equals(t)) {




                                if (dodajeX) {
                                    pocX += pomoc * vrednostX;
                                } else pocX -= pomoc * vrednostX;

                                if (dodajeY) pocY += pomoc * vrednostY;
                                else pocY -= pomoc * vrednostY;


                                try {
                                    k.setX(pocX);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                try {
                                    k.setY(pocY);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                                for (ConnectionPainter slikar : k.getConnectionList()) {
                                    Connection linkara = (Connection) slikar.getElement();
                                    if (linkara.getFirstTopic().equals(k)) {
                                        Point pos1 = new Point(pocX, pocY);
                                        slikar.setPos1(pos1);
                                    } else {
                                        Point pos2 = new Point(pocX, pocY);
                                        slikar.setPos2(pos2);
                                    }
                                }


                                Point pos1 = new Point(pocX, pocY);
                                painter.setPos1(pos1);

                                if (pocY == t.getY()) {
                                    if (dodajeX) {
                                        dodajeX = false;
                                    } else {
                                        dodajeX = true;
                                    }
                                }
                                if (pocX == t.getX()) {
                                    if (dodajeY) {
                                        dodajeY = false;
                                    } else dodajeY = true;
                                }
                                pomoc = 1;


                            }
                        }


                    }
                }


                for(ElementPainter p : MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getMindMap().getPainterList()){
                    if (!(p.getElement() instanceof Topic)) continue;
                    Topic p1 = (Topic) p.getElement();

                    if(p1.getNivo()==-1){

                        if (!(p1.getX() >= ostalePomoc * 3)) {
                            try {
                                p1.setX(p1.getX() + ostalePomoc);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                        for(ConnectionPainter slikar : p1.getConnectionList()){
                            Connection linkara = (Connection) slikar.getElement();
                            if (linkara.getFirstTopic().equals(p1)) {
                                Point pos1 = new Point(p1.getX(), p1.getY());
                                slikar.setPos1(pos1);
                            } else {
                                Point pos2 = new Point(p1.getX(), p1.getY());
                                slikar.setPos2(pos2);
                            }
                        }

                    }
                }

                red=1;
                while(true) {
                    int pomozi=0;

                    for (ElementPainter p : MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getMindMap().getPainterList()) {
                        if (!(p.getElement() instanceof Topic)) continue;
                        Topic p1 = (Topic) p.getElement();
                        if (p1.getNivo() == red) {
                            for (ElementPainter k : MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).getMindMap().getPainterList()) {
                                if (!(k.getElement() instanceof Topic)) continue;
                                Topic k1 = (Topic) k.getElement();

                                if (k1.getNivo() - 1 == red) {
                                    for (ConnectionPainter slikar : k1.getConnectionList()) {
                                        Connection linkara = (Connection) slikar.getElement();

                                        if (linkara.getFirstTopic().equals(p1) || linkara.getSecondTopic().equals(p1)) {
                                            pomozi++;




                                            if(p1.getX()==t.getX() && p1.getY() < t.getY()){
                                                try {
                                                    k1.setY(p1.getY()-75);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                                try {
                                                    k1.setX(p1.getX());
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }


                                                for (ConnectionPainter slikar1 : k1.getConnectionList()) {
                                                    Connection linkara1 = (Connection) slikar1.getElement();
                                                    if (linkara1.getFirstTopic().equals(k1)) {
                                                        Point pos1 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos1(pos1);
                                                    } else {
                                                        Point pos2 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos2(pos2);
                                                    }
                                                }

                                            }else if(p1.getX()==t.getX() && p1.getY() > t.getY()){
                                                try {
                                                    k1.setY(p1.getY()+75);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                                try {
                                                    k1.setX(p1.getX());
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }

                                                for (ConnectionPainter slikar1 : k1.getConnectionList()) {
                                                    Connection linkara1 = (Connection) slikar1.getElement();
                                                    if (linkara1.getFirstTopic().equals(k1)) {
                                                        Point pos1 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos1(pos1);
                                                    } else {
                                                        Point pos2 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos2(pos2);
                                                    }
                                                }

                                            }else if(p1.getX()<t.getX() && p1.getY() == t.getY()){
                                                try {
                                                    k1.setY(p1.getY());
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                                try {
                                                    k1.setX(p1.getX()-125);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }

                                                for (ConnectionPainter slikar1 : k1.getConnectionList()) {
                                                    Connection linkara1 = (Connection) slikar1.getElement();
                                                    if (linkara1.getFirstTopic().equals(k1)) {
                                                        Point pos1 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos1(pos1);
                                                    } else {
                                                        Point pos2 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos2(pos2);
                                                    }
                                                }

                                            }else if(p1.getX()>t.getX() && p1.getY() == t.getY()){
                                                try {
                                                    k1.setY(p1.getY() );
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                                try {
                                                    k1.setX(p1.getX()+125);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }

                                                for (ConnectionPainter slikar1 : k1.getConnectionList()) {
                                                    Connection linkara1 = (Connection) slikar1.getElement();
                                                    if (linkara1.getFirstTopic().equals(k1)) {
                                                        Point pos1 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos1(pos1);
                                                    } else {
                                                        Point pos2 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos2(pos2);
                                                    }
                                                }

                                            }else if(p1.getX()>t.getX() && p1.getY() > t.getY()){
                                                try {
                                                    k1.setY(p1.getY() + 50);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                                try {
                                                    k1.setX(p1.getX() + 50);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }

                                                for (ConnectionPainter slikar1 : k1.getConnectionList()) {
                                                    Connection linkara1 = (Connection) slikar1.getElement();
                                                    if (linkara1.getFirstTopic().equals(k1)) {
                                                        Point pos1 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos1(pos1);
                                                    } else {
                                                        Point pos2 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos2(pos2);
                                                    }
                                                }

                                            }else if(p1.getX()>t.getX() && p1.getY() < t.getY()){
                                                try {
                                                    k1.setY(p1.getY() - 50);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                                try {
                                                    k1.setX(p1.getX() + 50);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }

                                                for (ConnectionPainter slikar1 : k1.getConnectionList()) {
                                                    Connection linkara1 = (Connection) slikar1.getElement();
                                                    if (linkara1.getFirstTopic().equals(k1)) {
                                                        Point pos1 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos1(pos1);
                                                    } else {
                                                        Point pos2 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos2(pos2);
                                                    }
                                                }

                                            }else if(p1.getX()<t.getX() && p1.getY() > t.getY()){
                                                try {
                                                    k1.setY(p1.getY() + 50);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                                try {
                                                    k1.setX(p1.getX() - 50);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }

                                                for (ConnectionPainter slikar1 : k1.getConnectionList()) {
                                                    Topic linkara1 = (Topic) slikar1.getElement();
                                                    if (linkara1.getConnectionList().equals(k1)) {
                                                        Point pos1 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos1(pos1);
                                                    } else {
                                                        Point pos2 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos2(pos2);
                                                    }
                                                }

                                            }else if(p1.getX()<t.getX() && p1.getY() < t.getY()){
                                                try {
                                                    k1.setY(p1.getY() - 50);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }
                                                try {
                                                    k1.setX(p1.getX() - 50);
                                                } catch (IOException ex) {
                                                    throw new RuntimeException(ex);
                                                }

                                                for (ConnectionPainter slikar1 : k1.getConnectionList()) {
                                                    Connection linkara1 = (Connection) slikar1.getElement();
                                                    if (linkara1.getFirstTopic().equals(k1)) {
                                                        Point pos1 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos1(pos1);
                                                    } else {
                                                        Point pos2 = new Point(k1.getX(), k1.getY());
                                                        slikar1.setPos2(pos2);
                                                    }
                                                }

                                            }



                                        }

                                    }
                                }


                            }
                        }
                    }
                    if(pomozi==0) break;
                    else red++;
                }




            }




        }

    }
}
