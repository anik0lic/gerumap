package dsw.gerumap.app.core;

import dsw.gerumap.app.error.ConsoleLogger;
import dsw.gerumap.app.error.FileLogger;
import dsw.gerumap.app.maprepository.MapRepository;
import dsw.gerumap.app.message.MessageGenerator;
import dsw.gerumap.app.serializer.Serializer;
import dsw.gerumap.app.view.gui.Gui;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationFramework {
    private static ApplicationFramework instance;
    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator messageGenerator;
    protected ConsoleLogger consoleLogger;
    protected FileLogger fileLogger;
    protected Serializer serializer;

    private ApplicationFramework(){}

    public static ApplicationFramework getInstance(){
        if(instance == null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, MapRepository mapRepository, MessageGenerator messageGenerator, Serializer serializer){
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.messageGenerator = messageGenerator;
        this.serializer = serializer;

        consoleLogger = new ConsoleLogger();
        fileLogger = new FileLogger();

        this.messageGenerator.addSubscriber(gui);
        this.messageGenerator.addSubscriber(consoleLogger);
        this.messageGenerator.addSubscriber(fileLogger);
    }
}
