package oui.sncf.todo.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oui.sncf.todo.core.port.TaskRepository;
import oui.sncf.todo.core.task.*;
import oui.sncf.todo.usecases.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/newTask/{taskName}")
    public ResponseEntity<String> createTask(@PathVariable final String taskName){
        try {
            CreateTask createTask = new CreateTask(taskRepository);
            createTask.by(taskName);
            return new ResponseEntity<>("The task has been created", HttpStatus.CREATED);
        }catch (TaskAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/removalTask/{taskName}")
    public ResponseEntity<String> removeTask(@PathVariable final String taskName) {
        try {
            RemoveTask removeTask = new RemoveTask(taskRepository);
            removeTask.by(taskName);
            return new ResponseEntity<>("The task has been removed", HttpStatus.OK);
        }catch (TaskAlwaysTodoException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/task/{taskName}/newName/{newName}")
    public ResponseEntity<String> changeTaskName(
            @PathVariable final String taskName,
            @PathVariable final String newName
    ){
        try {
            ChangeTaskName change = new ChangeTaskName(taskRepository);
            change.of(taskName, newName);
            return new ResponseEntity<>("The name has been changed", HttpStatus.OK);
        }catch (TaskDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/task/{taskName}/newStatus/{newStatus}")
    public ResponseEntity<String> changeTaskStatus(
            @PathVariable final String taskName,
            @PathVariable final String newStatus
    ){
        try {
            ChangeTaskStatus change = new ChangeTaskStatus(taskRepository);
            change.of(taskName, TaskStatus.valueOf(newStatus));
            return new ResponseEntity<>("The status has been changed", HttpStatus.OK);
        }catch (TaskDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/task/{taskName}")
    public ResponseEntity<?> retrieveByName(@PathVariable("taskName") final String taskName){
        try {
            RetrieveTask retrieveTask = new RetrieveTask(taskRepository);
            Task task = retrieveTask.byName(taskName);
            return new ResponseEntity<>(task, HttpStatus.FOUND);
        }catch (TaskDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> retrieveTasks(@RequestParam String status){
        RetrieveTasks retrieveTasks = new RetrieveTasks(taskRepository);
        return (status.isEmpty()) ?
                new ResponseEntity<>(new ArrayList<>(retrieveTasks.retrieve(null)), HttpStatus.OK) :
                new ResponseEntity<>(new ArrayList<>(retrieveTasks.retrieve(TaskStatus.valueOf(status))), HttpStatus.OK);
    }
}
