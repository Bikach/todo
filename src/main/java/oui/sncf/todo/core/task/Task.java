package oui.sncf.todo.core.task;

import sun.invoke.empty.Empty;

import java.util.Objects;

public class Task {

    private String name;
    private String prefix = "";
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
        // todo validate status
        this.status = newStatus;
    }

    public void changeName(String newName) {
        // todo validate name
        this.name = newName;
    }

    public void isAlreadyInProgress() {
        if(this.status == TaskStatus.IN_PROGRESS)
            throw new TaskAlwaysInProgressException("The task is already in progress");
    }

    public void addPrefix(String prefix) {
        this.prefix = prefix;
        this.name = prefix +":"+ this.name;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) &&
                Objects.equals(prefix, task.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prefix);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", prefix='" + prefix + '\'' +
                ", status=" + status +
                '}';
    }
}
