package oui.sncf.todo.domain.task;

public class TaskDoesNotExistException extends RuntimeException{
    public TaskDoesNotExistException(String message) {
        super(message);
    }
}
