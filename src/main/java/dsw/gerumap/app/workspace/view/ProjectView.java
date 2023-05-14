package dsw.gerumap.app.workspace.view;

import dsw.gerumap.app.observer.Subscriber;
import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.MindMap;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.state.StateManager;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        stateManager = new StateManager();

        lblNameAndAuthor = new JLabel("Project (author)");
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        add(lblNameAndAuthor, BorderLayout.NORTH);
//        add(tabbedPane, BorderLayout.CENTER);
        add(lblNameAndAuthor);
        add(tabbedPane);
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
            project.removeSubscriber(this);

        project.addSubscriber(this);

        if (project == null) {
            tabbedPane.setVisible(false);
            return;
        }

        for (MapNode child: project.getChildren()){
            if(!child.getSubscribers().isEmpty()) {
                child.getSubscribers().clear();
            }
            MapView tab = new MapView((MindMap)child, tabbedPane.getTabCount());
            child.addSubscriber(tab);

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

        refreshTabs();
        updateLabel();
    }

//    public void startTopicState() {
//        this.stateManager.setTopicState();
//    }
//    public void startConnectionState() {
//        this.stateManager.setConnectionState();
//    }
//    public void startMoveState() {
//        this.stateManager.setMoveState();
//    }
//    public void startSelectState() {this.stateManager.setSelectionState();}

//    public void misKliknut(int x, int y, MapView m) throws IOException {
//        this.stateManager.getState().mousePressed(x, y, m, this);
//    }
//
//    public void misOtpusten(int x, int y, MapView m) throws IOException {
//        this.stateManager.getState().mouseReleased(x, y, m, this);
//    }
//
//    public void misPovucen(int x, int y, MapView m) throws IOException {
//        this.stateManager.getState().mouseDragged(x, y, m, this);
//    }

    @Override
    public void stateChanged(ChangeEvent e) {
        selectedIndex = tabbedPane.getSelectedIndex();
    }
}
