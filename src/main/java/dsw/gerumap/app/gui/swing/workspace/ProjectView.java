package dsw.gerumap.app.gui.swing.workspace;

import dsw.gerumap.app.observer.Subscriber;
import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.maprepository.implementation.MindMap;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Project;
import dsw.gerumap.app.state.StateManager;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectView extends JPanel implements Subscriber, ChangeListener {
    private JTabbedPane tabbedPane;
    private Project project;
    private List<MapView> tabs;
    private JLabel lblNameAndAuthor;
    private StateManager stateManager;
    private int selectedIndex;

    public ProjectView(){
        tabs = new ArrayList<>();
        lblNameAndAuthor = new JLabel("Project (author)");
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        stateManager = new StateManager();

        setLayout(new BorderLayout());

        add(lblNameAndAuthor, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }


    public void updateLabel() {
        if (project == null) {
            lblNameAndAuthor.setText("Project (author)");
            return;
        }

        lblNameAndAuthor.setText(project.getName() + " (" + project.getAuthor() + ")");
    }

    public void refreshTabs(){
        tabbedPane.removeAll();
        tabs.clear();

        if(!(project == null) && !(project.getSubscribers().isEmpty()))
            project.removeSubs(this);

        project.addSubs(this);

        if (project == null) {
            tabbedPane.setVisible(false);
            return;
        }
        System.out.println(project.getChildren());
        for (MapNode child: project.getChildren()){
            if(!child.getSubscribers().isEmpty()) {
                child.getSubscribers().clear();
            }
            MapView tab = new MapView((MindMap)child, tabbedPane.getTabCount());
            child.addSubs(tab);

            tabs.add(tab);
            tabbedPane.addTab(child.getName(), tab);
        }

        updateLabel();
        tabbedPane.setVisible(true);
        tabbedPane.addChangeListener(this);
    }

    public void clearTab(){
        tabbedPane.removeAll();
        tabs.clear();
        lblNameAndAuthor.setText("Project (author)");
    }

    @Override
    public void update(Object object) {
        if(project == null){
            return;
        }
        System.out.println("update pv");

        refreshTabs();
        updateLabel();
    }

    public void startTopicState() {
        this.stateManager.setTopicState();
    }
    public void startConnectionState() {
        this.stateManager.setConnectionState();
    }
    public void startMoveState() {
        this.stateManager.setMoveState();
    }
    public void startSelectState() {this.stateManager.setSelectionState();}

    public void misKliknut(int x, int y, MapView m) throws IOException {
        this.stateManager.getState().misKliknut(x, y, m);
    }

    public void misOtpusten(int x, int y, MapView m) throws IOException {
        this.stateManager.getState().misOtpusten(x, y, m);
    }

    public void misPovucen(int x, int y, MapView m) throws IOException {
        this.stateManager.getState().misPovucen(x, y, m);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        selectedIndex = tabbedPane.getSelectedIndex();
    }
}
