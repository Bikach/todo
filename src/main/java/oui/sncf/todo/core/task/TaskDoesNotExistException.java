package oui.sncf.todo.core.task;

public class TaskDoesNotExistException extends RuntimeException{
    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
