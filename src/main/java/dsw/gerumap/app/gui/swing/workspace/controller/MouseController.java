package dsw.gerumap.app.gui.swing.workspace.controller;

import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.view.MapView;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.observer.Subscriber;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.io.IOException;

public class MouseController extends MouseAdapter{

    @Override
    public void mousePressed(MouseEvent e) {
        Point position = getCoordinates(e);

        ProjectView projectView = MainFrame.getInstance().getProjectView();
        MindMap currMindMap = ((MapView) projectView.getTabbedPane().getSelectedComponent()).getMindMap();
        try {
            projectView.getStateManager().getState().mousePressed(position, currMindMap, projectView);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point position = getCoordinates(e);

        ProjectView projectView = MainFrame.getInstance().getProjectView();
        MindMap currMindMap = ((MapView) projectView.getTabbedPane().getSelectedComponent()).getMindMap();
        try {
            projectView.getStateManager().getState().mouseReleased(position, currMindMap, projectView);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point position = getCoordinates(e);

        ProjectView projectView = MainFrame.getInstance().getProjectView();
        MindMap currMindMap = ((MapView) projectView.getTabbedPane().getSelectedComponent()).getMindMap();
        try {
            projectView.getStateManager().getState().mouseDragged(position, currMindMap, projectView);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Point getCoordinates(MouseEvent me){
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        MapView currMapView = (MapView) projectView.getTabbedPane().getSelectedComponent();

        AffineTransform atInvert = null;
        try {
            atInvert = currMapView.getTransformation().createInverse();
        } catch(NoninvertibleTransformException | NullPointerException exception){
            System.err.print("Non invertible transformation");
        }

        Point2D pDest = atInvert.transform(new Point2D.Double(me.getX(), me.getY()), null);
        Point pDest2 = new Point();
        pDest2.x = (int) pDest.getX();
        pDest2.y = (int) pDest.getY();
        return pDest2;
    }
}
