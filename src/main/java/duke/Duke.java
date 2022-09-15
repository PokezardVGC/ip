package duke;
import duke.parser.Parser;
import duke.taskList.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * a chatbot that should be able to keep track of tasks
 * such as deadlines, events
 * and mark off as a task list
 */
public class Duke {
    /**
     * the main body of the code
     */
    public static void main(String[] args) {
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
        try {
            taskList = storage.readDuke(taskList.getTasks(), taskList.getCurr());
        } catch (Exception e) {
            ui.fileNotFoundPrint();
        }
        //main body
        String command = ui.readInput();
        while (!command.split(" ")[0].equals("bye")) {
            taskList = parser.readInput(command, taskList, ui);
            System.out.println(taskList.getToStage());
            command = ui.readInput();
        }

        //bye block
        storage.writerToDuke(taskList.getTasks(), taskList.getCurr());
        String byeString = ui.byePrint();
        System.out.println(byeString);
    }
}
