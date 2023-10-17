package oui.sncf.todo.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import oui.sncf.todo.core.domain.TaskRepository;
import oui.sncf.todo.core.domain.task.*;
import oui.sncf.todo.core.domain.task.exception.TaskAlreadyExistException;
import oui.sncf.todo.core.domain.task.exception.TaskAlwaysTodoException;
import oui.sncf.todo.core.domain.task.exception.TaskDoesNotExistException;
import oui.sncf.todo.core.usecases.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskApiController {

    private TaskRepository taskRepository;

    @PostMapping("/newTask/{taskName}")
    public ResponseEntity<String> createTask(@PathVariable final String taskName){
        try {
            new CreateTask(taskRepository).by(taskName);
            return new ResponseEntity<>("The task has been created", HttpStatus.CREATED);
        }catch (TaskAlreadyExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/removalTask/{taskName}")
    public ResponseEntity<String> removeTask(@PathVariable final String taskName) {
        try {
            new RemoveTask(taskRepository).by(taskName);
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
            new ChangeTaskName(taskRepository).of(taskName, newName);
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
            new ChangeTaskStatus(taskRepository).of(taskName, TaskStatus.valueOf(newStatus));
            return new ResponseEntity<>("The status has been changed", HttpStatus.OK);
        }catch (TaskDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/task/{taskName}")
    public ResponseEntity<?> retrieveByName(@PathVariable("taskName") final String taskName){
        try {
            Task task = new RetrieveTask(taskRepository).byName(taskName);
            return new ResponseEntity<>(task, HttpStatus.FOUND);
        }catch (TaskDoesNotExistException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> retrieveTasks(@RequestParam String status) {
        List<Task> tasks = getTasks(status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    private ArrayList<Task> getTasks(String status){
        if (status.isEmpty())
            return new ArrayList<>();
         return new ArrayList<>(new RetrieveTasks(taskRepository).retrieve(TaskStatus.valueOf(status.toUpperCase())));
    }

}
