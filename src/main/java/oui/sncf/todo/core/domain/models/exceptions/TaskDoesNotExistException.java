package oui.sncf.todo.core.domain.models.exceptions;

public class TaskDoesNotExistException extends RuntimeException{
    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
