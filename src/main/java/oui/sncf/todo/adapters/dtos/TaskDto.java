package oui.sncf.todo.adapters.dtos;

import oui.sncf.todo.core.task.TaskStatus;

import java.util.Objects;

public class TaskDto {

    private String name;
    private String prefix;
    private TaskStatus status;

    public TaskDto( String prefix, String name, TaskStatus status) {
        this.prefix = prefix;
        this.name = name;
        this.status = status;
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
        TaskDto taskDto = (TaskDto) o;
        return Objects.equals(name, taskDto.name) &&
                Objects.equals(prefix, taskDto.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prefix);
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "name='" + name + '\'' +
                ", prefix='" + prefix + '\'' +
                ", status=" + status +
                '}';
    }
}
