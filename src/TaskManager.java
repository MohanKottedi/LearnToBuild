import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TaskManager {
    private ArrayList<Task> tasks=new ArrayList<>();
    private int id=0;
    public void add(String name, int priority, LocalDateTime deadline){
        tasks.add(new Task(id++,name,priority,deadline));
    }
    public void delete(int id) {
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            if (it.next().id == id) {
                it.remove();
                break;
            }
        }
    }
    public void update(int id,String name,int priority,LocalDateTime deadline){
        for(Task t:tasks){
            if(t.id==id){
                t.deadline=deadline;
                t.priority=priority;
                t.name=name;
                break;
            }
        }
    }
    public void printAll(){
        System.out.println("Taks there :");
        Collections.sort(tasks);
        for(Task t:tasks) System.out.println(t.id+" "+t.name+" "+t.deadline+" "+t.priority);
    }

    public void markCompleted(int id){
        for(Task t:tasks){
            if(t.id==id) t.completed=true;
        }
    }
    public void getCompleted(){
        System.out.println("Completed Tasks :");
        for(Task t:tasks)
            if(t.completed)
                System.out.println(t.id+" "+t.name+" "+t.deadline+" "+t.priority);
    }
    public void getPendings(){
        System.out.println("Pending Tasks :");
        for(Task t:tasks)
            if(!t.completed)
                System.out.println(t.id+" "+t.name+" "+t.deadline+" "+t.priority);
    }
}
