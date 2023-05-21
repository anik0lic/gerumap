package dsw.gerumap.app.view.frame;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.controller.ActionManager;
import dsw.gerumap.app.maprepository.implementation.Project;
import dsw.gerumap.app.maprepository.implementation.ProjectExplorer;
import dsw.gerumap.app.tree.MapTree;
import dsw.gerumap.app.tree.MapTreeImplementation;
import dsw.gerumap.app.tree.model.MapTreeItem;
import dsw.gerumap.app.tree.view.MapTreeView;
import dsw.gerumap.app.workspace.view.ProjectView;
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
    private JToolBar toolbar;
    private JToolBar workToolbar;
    private MapTree mapTree;
    private MapTreeView projectExplorerTreeView;
    private ProjectExplorer projectExplorer;
    private ProjectView projectView;

    private MainFrame(){}

    private void initialise(){
        actionManager = new ActionManager();
        mapTree = new MapTreeImplementation();
        projectView = new ProjectView();
        initialiseGui();
    }

    private void initialiseGui(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize((int) (screenWidth / 1.5), (int) (screenHeight / 1.5));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeRuMap");

        menu = new Menu();
        toolbar = new Toolbar();
        workToolbar = new WorkToolbar();

        setJMenuBar(menu);
        add(toolbar, BorderLayout.NORTH);
        add(workToolbar, BorderLayout.EAST);

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

        projectExplorer = ApplicationFramework.getInstance().getMapRepository().getProjectExplorer();
        projectExplorerTreeView = mapTree.generateTree(projectExplorer);
        projectExplorer.addSubscriber(projectExplorerTreeView);

        JScrollPane scroll = new JScrollPane(projectExplorerTreeView);
        scroll.setMinimumSize(new Dimension(200, 150));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, rightPanel);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
    }
    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

}
