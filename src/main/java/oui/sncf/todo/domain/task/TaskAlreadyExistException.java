package oui.sncf.todo.domain.task;

public class TaskAlreadyExistException extends RuntimeException{
    public TaskAlreadyExistException(String message) {
        super(message);
    }
}
