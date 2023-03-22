package dsw.gerumap.app.gui.swing.view;

import javax.swing.*;

public class WorkToolbar extends JToolBar {

    public WorkToolbar () {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getSelectStateAction());
        add(MainFrame.getInstance().getActionManager().getMoveStateAction());
        add(MainFrame.getInstance().getActionManager().getTopicStateAction());
        add(MainFrame.getInstance().getActionManager().getConnectionStateAction());
        add(MainFrame.getInstance().getActionManager().getDeleteElementsAction());
        add(MainFrame.getInstance().getActionManager().getCentralTopicAction());
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());
        add(Box.createGlue());
        add(MainFrame.getInstance().getActionManager().getEditAction());
    }

}
