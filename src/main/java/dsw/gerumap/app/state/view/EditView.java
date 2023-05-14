package dsw.gerumap.app.state.view;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.workspace.elements.painters.ElementPainter;
import dsw.gerumap.app.message.EventType;
import dsw.gerumap.app.view.frame.MainFrame;
import dsw.gerumap.app.workspace.view.MapView;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;

public class EditView extends JDialog{
    JButton btnColor;
    JButton btnStroke;
    JButton btnName;
    JButton btnExit;

    public EditView(Frame owner, String title, boolean modal) throws IOException {
        super(owner, title, modal);

        initialise();

        setSize(400, 200);
        setLocationRelativeTo(owner);
    }

    private void initialise() throws IOException {
        JPanel mainPanel = new JPanel(new GridLayout(2, 2));

        mainPanel.setBorder(new TitledBorder(new EtchedBorder(), "Edit"));

        btnColor = new JButton("Set color");
        btnStroke = new JButton("Set stroke");
        btnName = new JButton("Set name");
        btnExit = new JButton("Exit");

        mainPanel.add(btnColor);
        mainPanel.add(btnStroke);
        mainPanel.add(btnName);
        mainPanel.add(btnExit);

        btnColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);

            MapView map = (MapView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

            for(ElementPainter element : map.getSelectionModel().getSelected()){
                try {
                    element.getElement().setColor2(newColor);
                    element.getElement().setColor(newColor);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
//                map.getSelectionModel().setOldColor(newColor);
            }
            this.setVisible(false);
        });

        btnStroke.addActionListener(e -> {
            String strStroke = JOptionPane.showInputDialog(MainFrame.getInstance(), "Input new stroke");
            if(strStroke == null){
                return;
            }
            int stroke;
            try{
                stroke = Integer.parseInt(strStroke);
            }catch (NumberFormatException ex){
                try {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.ENTER_INTEGER);
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
                return;
            }

            if(stroke < 0 || stroke > 3){
                try {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.INVALID_SIZE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }

            MapView map = (MapView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

            for (ElementPainter element : map.getSelectionModel().getSelected()) {
                try {
                    element.getElement().setStroke(stroke);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            this.setVisible(false);
        });

        btnName.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Input text");
            if(name.length() > 10){
                try {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage(EventType.INVALID_NAME_SIZE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
            MapView map = (MapView) MainFrame.getInstance().getProjectView().getTabbedPane().getSelectedComponent();

            for (ElementPainter element : map.getSelectionModel().getSelected()) {
                try {
                    element.getElement().setName(name);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            this.setVisible(false);
        });

        btnExit.addActionListener(e -> {
            this.setVisible(false);
        });


        add(mainPanel);
    }

}
