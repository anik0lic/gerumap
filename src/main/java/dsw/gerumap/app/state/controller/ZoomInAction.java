package dsw.gerumap.app.state.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.message.EventType;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ZoomInAction extends AbstractGeRuMapAction {

    public ZoomInAction() {
        putValue(SMALL_ICON, loadIcon("/images/zoom.png"));
        putValue(NAME, "Zoom");
        putValue(SHORT_DESCRIPTION, "Zoom");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getProjectView().getTabs().isEmpty()){
            try {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.OPEN_MIND_MAP);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else{
            MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex()).zoomIn();
        }
    }
}
