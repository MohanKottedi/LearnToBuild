import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Comparable<Task>{
    int id;
    String name;
    int priority;
    LocalDateTime deadline;
    boolean completed;
    public Task(int id, String name, int priority, LocalDateTime deadline) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
    }

    public Task(String name) {
        this.name = name;
        this.priority = 4;
        this.deadline = LocalDateTime.now().plusDays(6);
    }
    public int compareTo(Task a) {
        if (Math.abs(this.deadline.toLocalDate().toEpochDay() - a.deadline.toLocalDate().toEpochDay()) < 7) {
            return a.priority - this.priority;
        } else return  Long.compare(this.deadline.toLocalDate().toEpochDay(),a.deadline.toLocalDate().toEpochDay());
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id==task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
