import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args){
        TaskManager taskManager=new TaskManager();
        taskManager.add("Quiz4",4, LocalDateTime.now().plusDays(3));
        taskManager.add("DigitalAssignment",1, LocalDateTime.now().plusDays(3));
        taskManager.add("Quiz2",1, LocalDateTime.now().plusDays(15));
        taskManager.printAll();
        taskManager.update(1,"QUIZ1DSA",3,LocalDateTime.now().plusMonths(1));
        taskManager.printAll();
        taskManager.markCompleted(2);
        taskManager.getPendings();
        taskManager.getCompleted();
    }
}
