package dsw.gerumap.app.state.controller;

import dsw.gerumap.app.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.state.StateManager;
import dsw.gerumap.app.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class ConnectionStateAction extends AbstractGeRuMapAction {

    public ConnectionStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/connection.png"));
        putValue(NAME, "Connect");
        putValue(SHORT_DESCRIPTION, "Connect");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        MainFrame.getInstance().getProjectView().startConnectionState();
        StateManager stateManager = MainFrame.getInstance().getProjectView().getStateManager();
        stateManager.setConnectionState();
    }

    @Override
    public boolean accept(Object sender) {
        return super.accept(sender);
    }
}
