package oui.sncf.todo.core.domain.tasks.models;

public class TaskAlReadyExistException extends RuntimeException {
    public TaskAlReadyExistException(String message) {
        super(message);
    }
}
