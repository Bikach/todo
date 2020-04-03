package oui.sncf.todo.core.domain.models.exceptions;

public class TaskAlReadyExistException extends RuntimeException {
    public TaskAlReadyExistException(String message) {
        super(message);
    }
}
