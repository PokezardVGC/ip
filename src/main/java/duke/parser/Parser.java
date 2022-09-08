package duke.parser;

import duke.taskList.TaskList;
import duke.ui.Ui;

public class Parser {

    public TaskList readInput(String command, TaskList taskList, Ui ui) {
        switch (command.split(" ")[0]) {
            case "list":
                return taskList.listTasks(ui);

            case "mark":
                try {
                    return taskList.markTask(command.split(" "), ui);
                } catch (Exception e) {
                    ui.emptyMarkPrint();
                }

            case "unmark":
                try {
                    return taskList.unmarkTask(command.split(" "), ui);
                } catch (Exception e) {
                    ui.emptyUnmarkPrint();
                }

            case "delete":
                try {
                    return taskList.deleteTask(command.split(" "), ui);
                } catch (Exception e) {
                    ui.emptyDeletePrint();
                }

            case "todo":
                try {
                    return taskList.toDoTask(command, ui);
                } catch (Exception e) {
                    ui.emptyToDoPrint();
                }

            case "deadline":
                try {
                    return taskList.deadlineTask(command, ui);
                } catch (Exception e) {
                    ui.emptyDeadlinePrint();
                }

            case "event":
                try {
                    return taskList.eventTask(command, ui);
                } catch (Exception e) {
                    ui.emptyEventPrint();
                }

            case "things":
                return taskList.thingsTask(command);
            case "":
                return taskList;
            default:
                ui.addUnknownPrint();
                return taskList;
        }
    }
}
