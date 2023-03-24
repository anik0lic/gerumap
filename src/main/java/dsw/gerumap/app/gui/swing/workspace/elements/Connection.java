package dsw.gerumap.app.gui.swing.workspace.elements;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Element;
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
    private float xF, yF, xS, yS;

    public Connection(String name, MapNode parent, Color color, int stroke, Topic firstTopic) {
        super(name, parent, color, stroke);
        setFirstTopic(firstTopic);
    }

    public void setFirstTopic(Topic firstTopic) {
        this.firstTopic = firstTopic;
        xF = firstTopic.getX();
        yF = firstTopic.getY();
    }

    public void setSecondTopic(Topic secondTopic) {
        this.secondTopic = secondTopic;
        xS = secondTopic.getX();
        yS = secondTopic.getY();
    }

    public void setNewCoordinates(){
        if(secondTopic == null) return;
        xF = firstTopic.getX();
        yF = firstTopic.getY();
        xS = secondTopic.getX();
        yS = secondTopic.getY();
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
