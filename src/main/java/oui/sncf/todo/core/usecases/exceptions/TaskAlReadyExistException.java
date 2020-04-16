package oui.sncf.todo.core.usecases.exceptions;

public class TaskAlReadyExistException extends RuntimeException {
    public TaskAlReadyExistException(String message) {
        super(message);
    }
}
