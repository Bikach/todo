package oui.sncf.todo.domain.task;

import java.util.Objects;

public class Task {

    private String name;
    private TaskStatus status;

    public Task(String name) {
        this.name = name;
        this.status = TaskStatus.TODO;
    }

    public Task(final String name, final TaskStatus status){
        this.name = name;
        this.status = status;
    }

    public void changeStatus(TaskStatus newStatus) {
        // TODO validate status ?
        this.status = newStatus;
    }

    public void changeName(String newName) {
        // todo validate name ?
        this.name = newName;
    }

    public void alreadyExist() {
        throw new TaskAlreadyExistException("The task already exist.");
    }

    public void isAlreadyTodo() {
        if(this.status == TaskStatus.TODO)
            throw new TaskAlwaysTodoException("The task is already todo");
    }

    public String getName() {
        return name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) &&
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
