package oui.sncf.todo.adapters.dtos;

import oui.sncf.todo.domain.task.TaskStatus;

public class TaskDtoBuilder {

    private String name;
    private TaskStatus status;

    public TaskDtoBuilder() {}

    public TaskDto build(){
        return new TaskDto(this.name, this.status);
    }

    public TaskDtoBuilder name(String name){
        this.name = name;
        return this;
    }

    public TaskDtoBuilder status(TaskStatus status){
        this.status = status;
        return this;
    }
}
