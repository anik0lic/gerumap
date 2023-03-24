package dsw.gerumap.app.gui.swing.state.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.workspace.view.MapView;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import dsw.gerumap.app.message.EventType;
import dsw.gerumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ZoomOutAction extends AbstractGeRuMapAction {

    public ZoomOutAction() {
        putValue(SMALL_ICON, loadIcon("/images/zoomout.png"));
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out");
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
            ProjectView projectView = MainFrame.getInstance().getProjectView();
            int selectedMapIndex = projectView.getTabbedPane().getSelectedIndex();
            MapView currMapView = projectView.getTabs().get(selectedMapIndex);
//            currMapView.zoomOut();
        }
    }

}
