import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Builder
public class Task implements Comparable<Task>{
    int id;
    String name;
    int priority;
    LocalDateTime deadline;
    boolean completed;
    public Task(int id, String name, int priority, LocalDateTime deadline,boolean completed) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.completed=completed;
    }
    public Urgency getUrgency(){
        long days= ChronoUnit.DAYS.between(LocalDate.now(),this.deadline);
        if(days<3) return Urgency.High;
        else if(days<5) return Urgency.MEDIUM;
        else return  Urgency.LOW;
    }
    public int compareTo(Task a) {
        if (Math.abs(this.deadline.toLocalDate().toEpochDay() - a.deadline.toLocalDate().toEpochDay()) < 7) {
            return this.priority - a.priority;
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
