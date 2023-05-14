package dsw.gerumap.app.state.controller;

import dsw.gerumap.app.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.state.StateManager;
import dsw.gerumap.app.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class MoveStateAction extends AbstractGeRuMapAction {

    public MoveStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/move.png"));
        putValue(NAME, "Move element");
        putValue(SHORT_DESCRIPTION, "Move element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getProjectView().getStateManager();
        stateManager.setMoveState();
    }
}
