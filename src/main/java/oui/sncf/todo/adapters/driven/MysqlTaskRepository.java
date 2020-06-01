package oui.sncf.todo.adapters.driven;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import oui.sncf.todo.adapters.driven.dtos.TaskDto;
import oui.sncf.todo.adapters.driven.dtos.TaskDtoBuilder;
import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class MysqlTaskRepository  implements TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public MysqlTaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Task task) {
        TaskDto taskDto = new TaskDtoBuilder().name(task.getName()).status(task.getStatus()).build();
        jdbcTemplate.update("INSERT INTO taskDto (name, status) VALUES (?, ?)", taskDto.getName(), taskDto.getStatus().toString());
    }

    @Override
    public void remove(Task task) {
        jdbcTemplate.update("DELETE FROM taskDto WHERE name = ?", task.getName());
    }

    @Override
    public Optional<Task> getByName(String name) {
        Optional<Task> optionalTask;
        try{
            optionalTask = jdbcTemplate.queryForObject(
                    "select * from taskDto where name = ?",
                    new Object[]{name},
                    (rs, rowNum) -> Optional.of(new Task(
                            rs.getString("name"),
                            TaskStatus.valueOf(rs.getString("status"))
                    ))
            );
        }catch (EmptyResultDataAccessException e){
            optionalTask = Optional.empty();
        }
        return optionalTask;
    }

    @Override
    public Set<Task> fetch(TaskStatus status) {
        Optional<TaskStatus> optionalStatus = Optional.ofNullable(status);
        return  optionalStatus.map(taskStatus -> unfilteredTasks()
                .stream()
                .filter(taskDto -> taskDto.getStatus().equals(taskStatus))
                .map(taskDto ->  new Task(taskDto.getName(), taskDto.getStatus()))
                .collect(Collectors.toCollection(LinkedHashSet::new)))
                .orElseGet(this::unfilteredTasks);

    }

    private LinkedHashSet<Task> unfilteredTasks(){
        return new LinkedHashSet<>(jdbcTemplate.query(
                "select * from taskDto",
                (rs, rowNum) -> new Task(
                        rs.getString("name"),
                        TaskStatus.valueOf(rs.getString("status"))
                )
        ));
    }
}
