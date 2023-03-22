package dsw.gerumap.app.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationFramework {

    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator messageGenerator;
    protected Serializer serializer;

    private static ApplicationFramework instance;
    private ApplicationFramework() {

    }

    public void run (){this.gui.start();};

    public void initialise (Gui gui, MapRepository mapRepository, MessageGenerator messageGenerator, Serializer serializer) {
        this.gui = gui;
        this.mapRepository= mapRepository;
        this.messageGenerator = messageGenerator;
        this.serializer = serializer;
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
