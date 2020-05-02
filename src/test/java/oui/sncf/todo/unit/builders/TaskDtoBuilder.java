package oui.sncf.todo.unit.builders;

import oui.sncf.todo.adapters.dtos.TaskDto;
import oui.sncf.todo.core.task.TaskStatus;

public class TaskDtoBuilder {

    private String name;
    private String prefix;
    private TaskStatus status;

    public TaskDtoBuilder() {}

    public TaskDto build(){
        return new TaskDto(this.prefix, this.name, this.status);
    }

    public TaskDtoBuilder name(String name){
        this.name = name;
        return this;
    }

    public TaskDtoBuilder prefix(String prefix){

        this.prefix = prefix;
        return this;
    }

    public TaskDtoBuilder status(TaskStatus status){
        this.status = status;
        return this;
    }
}
