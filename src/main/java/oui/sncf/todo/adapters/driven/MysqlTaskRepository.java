package oui.sncf.todo.adapters.driven;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import oui.sncf.todo.adapters.driven.dtos.TaskDto;
import oui.sncf.todo.adapters.driven.dtos.TaskDtoBuilder;
import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.util.Optional;
import java.util.Set;

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
        return Optional.empty();
    }

    @Override
    public Set<Task> fetch(TaskStatus status) {
        return null;
    }
}
