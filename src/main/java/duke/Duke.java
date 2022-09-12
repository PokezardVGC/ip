package duke;
import duke.parser.Parser;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * a chatbot that should be able to keep track of tasks
 * such as deadlines, events
 * and mark off as a task list
 */
public class Duke extends Application {
    /**
     * the main body of the code
     */
    public void start(Stage stage) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        Parser parser = new Parser();
        TaskList taskList = new TaskList();

        String greetings = ui.greetingsPrint();
        System.out.println(greetings);

        //file creation
        try {
            String fileCreated;
            fileCreated = Storage.createFiles();
            System.out.println(fileCreated);
        } catch (Exception e) {
            String fileErrorString = ui.fileErrorPrint();
            System.out.println(fileErrorString);
        }

        //file reading
        taskList = storage.readDuke(taskList.getTasks(), taskList.getCurr());

        //main body
        String command = ui.readInput();
        while (!command.split(" ")[0].equals("bye")) {
            taskList = parser.readInput(command, taskList, ui);
            Label output = new Label(taskList.getToStage());
            Scene scene = new Scene(output);
            stage.setScene(scene);
            stage.show();
            command = ui.readInput();
        }

        //bye block
        storage.writerToDuke(taskList.getTasks(), taskList.getCurr());
        String byeString = ui.byePrint();
        System.out.println(byeString);
    }
}
