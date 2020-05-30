package oui.sncf.todo.adapters.driven.dtos;

import oui.sncf.todo.domain.task.TaskStatus;

import java.util.Objects;

public class TaskDto {

    private String id;
    private final String name;
    private final TaskStatus status;

    public TaskDto( String name, TaskStatus status) {
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
                status == taskDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status);
    }
}
