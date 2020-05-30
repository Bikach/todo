package oui.sncf.todo.adapters.driven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import oui.sncf.todo.domain.port.TaskRepository;
import oui.sncf.todo.domain.task.Task;
import oui.sncf.todo.domain.task.TaskStatus;

import java.util.Optional;
import java.util.Set;

@Repository
public class MysqlTaskRepository  implements TaskRepository {


    private JdbcTemplate jdbcTemplate;

    public MysqlTaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Task task) {

    }

    @Override
    public void remove(Task task) {

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
