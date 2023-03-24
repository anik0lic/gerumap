package dsw.gerumap.app.gui.swing.state.controller;

import dsw.gerumap.app.gui.swing.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.state.StateManager;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class TopicStateAction extends AbstractGeRuMapAction {

    public TopicStateAction() {;
        putValue(SMALL_ICON, loadIcon("/images/topic.png"));
        putValue(NAME, "AddElement");
        putValue(SHORT_DESCRIPTION, "AddElement");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getProjectView().getStateManager();
        stateManager.setTopicState();
//        MainFrame.getInstance().getProjectView().startTopicState();
    }
}
