package dsw.gerumap.app.state.controller;

import dsw.gerumap.app.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.state.StateManager;
import dsw.gerumap.app.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class SelectStateAction extends AbstractGeRuMapAction {

    public SelectStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/select.png"));
        putValue(NAME, "Select");
        putValue(SHORT_DESCRIPTION, "Select");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getProjectView().getStateManager();
        stateManager.setSelectionState();
//        MainFrame.getInstance().getProjectView().startSelectState();
    }
}
