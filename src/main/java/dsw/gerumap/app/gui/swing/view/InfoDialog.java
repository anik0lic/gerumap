package dsw.gerumap.app.gui.swing.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;

public class InfoDialog extends JDialog {
    public InfoDialog(Frame owner, String title, boolean modal) throws IOException {
        super(owner, title, modal);

        initialise();

        setSize(700, 500);
        setLocationRelativeTo(owner);
    }

    private void initialise() throws IOException {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        mainPanel.setBorder(new TitledBorder(new EtchedBorder(), "Info"));
        leftPanel.setBorder(new TitledBorder(new EtchedBorder()));
        rightPanel.setBorder(new TitledBorder(new EtchedBorder()));

        Box box1 = Box.createVerticalBox();
        Box box2 = Box.createVerticalBox();

        box1.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/woman.png")))));
        box1.add(Box.createVerticalStrut(50));
        box1.add(new JLabel("Andjela Nikolic RN 115/22"));

        box2.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/man.png")))));
        box2.add(Box.createVerticalStrut(50));
        box2.add(new JLabel("Stefan Milic RN 122/22"));

        leftPanel.add(box1);
        rightPanel.add(box2);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);
    }
}
