package dsw.gerumap.app.controller;


import dsw.gerumap.app.controller.actions.*;
import dsw.gerumap.app.state.controller.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager {

    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private DeleteAction deleteAction;
    private InfoAction infoAction;
    private AuthorAction authorAction;
    private ConnectionStateAction connectionStateAction;
    private TopicStateAction topicStateAction;
    private MoveStateAction moveStateAction;
    private SelectStateAction selectStateAction;
    private DeleteElementsAction deleteElementsAction;
    private EditAction editAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private OpenAction openAction;
    private SaveAction saveAction;
    private ExportAction exportAction;


    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        deleteAction = new DeleteAction();
        infoAction = new InfoAction();
        authorAction = new AuthorAction();
        connectionStateAction = new ConnectionStateAction();
        topicStateAction = new TopicStateAction();
        moveStateAction = new MoveStateAction();
        selectStateAction = new SelectStateAction();
        deleteElementsAction = new DeleteElementsAction();
        editAction = new EditAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        openAction = new OpenAction();
        saveAction = new SaveAction();
        exportAction = new ExportAction();
    }
}
