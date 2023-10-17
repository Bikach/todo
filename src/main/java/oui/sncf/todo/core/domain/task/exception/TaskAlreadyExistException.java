package oui.sncf.todo.core.domain.task.exception;

public class TaskAlreadyExistException extends RuntimeException{
    public TaskAlreadyExistException(String message) {
        super(message);
    }
}
