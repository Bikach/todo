package oui.sncf.todo.core.domain.models;

public class TaskAlReadyExistException extends RuntimeException {
    public TaskAlReadyExistException(String message) {
        super(message);
    }
}
