package dsw.gerumap.app.gui.swing.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.gui.swing.controller.ActionManager;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.gui.swing.tree.MapTree;
import dsw.gerumap.app.gui.swing.tree.MapTreeImplementation;
import dsw.gerumap.app.gui.swing.tree.model.MapTreeItem;
import dsw.gerumap.app.gui.swing.tree.view.MapTreeView;
import dsw.gerumap.app.gui.swing.workspace.view.ProjectView;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
@Setter
public class MainFrame extends JFrame{

    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private JToolBar workToolBar;
    private MapTree mapTree;
    private MapTreeView projectExplorer;
    private ProjectView projectView;

    private  MainFrame () { }

    private void initialise () {
        actionManager = new ActionManager();
        mapTree = new MapTreeImplementation();
        projectView = new ProjectView();
        projectExplorer = mapTree.generateTree(ApplicationFramework.getInstance().getMapRepository().getProjectExplorer());
        initialiseGui(projectExplorer);

    }

    private void initialiseGui(MapTreeView projectExplorer) {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeRuMap");

        menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new Toolbar();
        add(toolBar, BorderLayout.NORTH);

        workToolBar = new WorkToolbar();
        add(workToolBar, BorderLayout.EAST);

        JPanel rightPanel = projectView;
        rightPanel.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    MapTreeItem selected = MainFrame.getInstance().getMapTree().getSelectedNode();


                    if(selected.getMapNode() instanceof Project){
                        MainFrame.getInstance().getProjectView().refreshTabs();
                        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
                    }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200, 150));
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, rightPanel);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
    }

    public static MainFrame getInstance() {

        if (instance == null) {
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

}
