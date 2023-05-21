package dsw.gerumap.app;

import dsw.gerumap.app.core.*;
import dsw.gerumap.app.maprepository.MapRepository;
import dsw.gerumap.app.message.MessageGenerator;
import dsw.gerumap.app.serializer.JacksonSerializer;
import dsw.gerumap.app.serializer.Serializer;
import dsw.gerumap.app.view.gui.Gui;
import dsw.gerumap.app.view.gui.SwingGui;
import dsw.gerumap.app.maprepository.MapRepositoryImplementation;
import dsw.gerumap.app.message.MessageGeneratorImplementation;
//import dsw.gerumap.app.serializer.GsonSerializer;

public class AppCore  {
    public static void main(String[] args) {
        Gui gui = new SwingGui();
        MapRepository mapRepository = new MapRepositoryImplementation();
        MessageGenerator messageGenerator = new MessageGeneratorImplementation();
//        Serializer serializer = new GsonSerializer();
        Serializer serializer = new JacksonSerializer();

        ApplicationFramework appCore = ApplicationFramework.getInstance();

        appCore.initialise(gui, mapRepository, messageGenerator, serializer);
        appCore.run();
    }
}
