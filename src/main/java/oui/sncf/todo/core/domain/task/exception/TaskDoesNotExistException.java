package oui.sncf.todo.core.domain.task.exception;

public class TaskDoesNotExistException extends RuntimeException{
    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
