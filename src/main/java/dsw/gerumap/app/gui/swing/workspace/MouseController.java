package dsw.gerumap.app.gui.swing.workspace;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.observer.Subscriber;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MouseController extends MouseAdapter implements Subscriber{
    MapView mapView;

    public MouseController(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point position = e.getPoint();
        double scaling = mapView.getScalingf();
        int x = (int) (position.x / scaling);
        int y = (int) (position.y / scaling);
        try {
            MainFrame.getInstance().getProjectView().misKliknut(x, y, mapView);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point position = e.getPoint();
        double scaling = mapView.getScalingf();
        int x = (int) (position.x / scaling);
        int y = (int) (position.y / scaling);
        try {
            MainFrame.getInstance().getProjectView().misOtpusten(x, y, mapView);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point position = e.getPoint();
        double scaling = mapView.getScalingf();
        int x = (int) (position.x / scaling);
        int y = (int) (position.y / scaling);
        try {
            MainFrame.getInstance().getProjectView().misPovucen(x, y, mapView);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Object object) throws IOException {
    }
}
