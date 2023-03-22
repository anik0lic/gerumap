package dsw.gerumap.app.state.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.commands.AbstractCommand;
import dsw.gerumap.app.gui.swing.commands.implementation.RemoveElementCommand;
import dsw.gerumap.app.gui.swing.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Element;
import dsw.gerumap.app.gui.swing.message.EventType;
import dsw.gerumap.app.gui.swing.view.MainFrame;
import dsw.gerumap.app.gui.swing.workspace.MapView;
import dsw.gerumap.app.gui.swing.workspace.panel.Topic;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ConnectionPainter;
import dsw.gerumap.app.gui.swing.workspace.panel.painters.ElementPainter;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteElementsAction extends AbstractGeRuMapAction {

    public DeleteElementsAction() {
        putValue(SMALL_ICON, loadIcon("/images/deleteTopic.png"));
        putValue(NAME, "Remove");
        putValue(SHORT_DESCRIPTION, "Remove");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getProjectView().getTabs().isEmpty()) {
            try {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.OPEN_MIND_MAP);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            List<ElementPainter> nova = new ArrayList<>();

            MapView map = MainFrame.getInstance().getProjectView().getTabs().get(MainFrame.getInstance().getProjectView().getSelectedIndex());
            for (ElementPainter p : map.getMindMap().getPainterList()) {
                for (Element el : map.getSelectionModel().getSelected()) {
                    if (p.getElement().equals(el)) {
                        if (p.getElement() instanceof Topic) {
                            Topic t = (Topic) p.getElement();
                            for (ConnectionPainter painter : t.getConnectionList()) {
                                nova.add(painter);
                            }
                        }
                        nova.add(p);
                    }
                }
            }
            AbstractCommand command = new RemoveElementCommand(nova, map);
            try {
                ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
