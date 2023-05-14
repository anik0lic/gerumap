package dsw.gerumap.app.workspace.controller;

import dsw.gerumap.app.view.frame.MainFrame;
import dsw.gerumap.app.workspace.view.MapView;
import dsw.gerumap.app.workspace.view.ProjectView;
import dsw.gerumap.app.maprepository.implementation.MindMap;

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
        Point position = e.getPoint();

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
        Point position = e.getPoint();

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
        Point position = e.getPoint();

        ProjectView projectView = MainFrame.getInstance().getProjectView();
        MindMap currMindMap = ((MapView) projectView.getTabbedPane().getSelectedComponent()).getMindMap();
        try {
            projectView.getStateManager().getState().mouseDragged(position, currMindMap, projectView);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
