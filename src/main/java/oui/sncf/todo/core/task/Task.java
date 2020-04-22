package oui.sncf.todo.core.task;

import java.util.Objects;

public class Task {

    private final String name;
    private TaskStatus status;

    public Task(String name) {
        this.name = name;
        this.status = TaskStatus.IN_PROGRESS;
    }

    public Task(final String name, final TaskStatus status){
        this.name = name;
        this.status = status;
    }

    public void changeStatus(TaskStatus newStatus) {
        // validation metier
        this.status = newStatus;
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
        return Objects.equals(name, task.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", statue=" + status +
                '}';
    }
}
