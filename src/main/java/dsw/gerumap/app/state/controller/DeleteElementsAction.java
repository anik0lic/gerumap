package dsw.gerumap.app.state.controller;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.commands.AbstractCommand;
import dsw.gerumap.app.commands.implementation.RemoveElementCommand;
import dsw.gerumap.app.controller.AbstractGeRuMapAction;
import dsw.gerumap.app.message.EventType;
import dsw.gerumap.app.view.frame.MainFrame;
import dsw.gerumap.app.workspace.view.MapView;
import dsw.gerumap.app.workspace.elements.painters.ElementPainter;

import java.awt.event.ActionEvent;
import java.io.IOException;
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
            List<ElementPainter> paintersForDelete;
            MapView map = (MapView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();
            paintersForDelete = map.getSelectionModel().getSelected();

            AbstractCommand command = new RemoveElementCommand(paintersForDelete, map);
            try {
                ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
//            for (ElementPainter p : map.getMindMap().getPainterList()) {
//                for (ElementPainter el : map.getSelectionModel().getSelected()) {
//                    if (p.equals(el)) {
//                        if (p.getElement() instanceof Topic) {
//                            Topic t = (Topic) p.getElement();
//                            nova.addAll(t.getConnectionList());
//                        }
//                        nova.add(p);
//                    }
//                }
//            }
//            AbstractCommand command = new RemoveElementCommand(nova, map);
//            try {
//                ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }

        }
    }

}
