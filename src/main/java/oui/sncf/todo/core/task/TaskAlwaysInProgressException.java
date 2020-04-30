package oui.sncf.todo.core.task;

public class TaskAlwaysInProgressException extends RuntimeException {
    public TaskAlwaysInProgressException(String message) {
        super(message);
    }
}
