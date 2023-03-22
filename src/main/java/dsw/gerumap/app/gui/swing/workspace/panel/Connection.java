package dsw.gerumap.app.gui.swing.workspace.panel;

import dsw.gerumap.app.gui.swing.maprepository.composite.MapNode;
import dsw.gerumap.app.gui.swing.maprepository.implementation.Element;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@NoArgsConstructor
@Getter
@Setter
public class Connection extends Element {
    private Topic firstTopic;
    private Topic secondTopic;

    public Connection(String name, MapNode parent, Color color, int stroke, Topic firstTopic, Topic secondTopic) {
        super(name, parent, color, stroke);
        this.firstTopic = firstTopic;
        this.secondTopic = secondTopic;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Connection) {
            Connection otherObj = (Connection) obj;
            return ((this.getFirstTopic().equals(otherObj.getFirstTopic()) && this.getSecondTopic().equals(otherObj.getSecondTopic())) ||
                    (this.getFirstTopic().equals(otherObj.getSecondTopic()) && this.getSecondTopic().equals(otherObj.getFirstTopic())));
        }
        return false;
    }
}
