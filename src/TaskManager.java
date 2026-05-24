import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TaskManager {
    private int id=0;
    ArrayList<Task> tasks;
    public void loadTasks(){
        tasks=new ArrayList<>();
        try(BufferedReader bw=new BufferedReader(new FileReader("data.csv"))){
            String a;
            while((a=bw.readLine())!=null){
                String para[]=a.split(",");
                if(para.length!=5) continue;
                tasks.add(Task.builder()
                        .id(Integer.parseInt(para[0]))
                        .name(para[1])
                        .priority(Integer.parseInt(para[2]))
                        .deadline(LocalDateTime.parse(para[3]))
                        .build());
            }
            System.out.println("Loaded Sucessfulyy...");
        }catch(IOException e){
            System.out.println("Error while loading csv file...");
        }
    }
    public void saveTasks(){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("data.csv"))){
            bw.write("id,name,priority,deadline");
            for(Task i:tasks){
                bw.newLine();
                bw.write(i.id+","+i.name+","+i.priority+","+i.deadline);
            }
            System.out.println("Saved Sucessfulyy...");
        }catch(IOException e){
            System.out.println("Error while saving...");
        }
    }
    public void add(String name, int priority, LocalDateTime deadline){
       if(tasks==null) loadTasks();
       tasks.add(Task.builder()
               .id(id++)
               .name(name)
               .priority(priority)
               .deadline(deadline)
               .build());
       saveTasks();
    }
    public void delete(int id) {
        if(tasks==null) loadTasks();
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).id==id) {tasks.remove(i);break;}
        }
    }
    public void update(int id,String name,int priority,LocalDateTime deadline){
        if(tasks==null) loadTasks();
        for(Task task:tasks){
            if(task.id==id){
                task.name=name;
                task.priority=priority;
                task.deadline=deadline;
                break;
            }
        }
    }
    public void printAll(){
        if(tasks==null) loadTasks();
        Collections.sort(tasks);
        for(Task task:tasks){
            System.out.printf("%d  |  %s  |  %d  |  %s\n"
                    ,task.id,task.name,task.priority,task.deadline);
        }
    }

//    public void markCompleted(int id){
//        for(Task t:tasks){
//            if(t.id==id) t.completed=true;
//        }
//    }
//    public void getCompleted(){
//        System.out.println("Completed Tasks :");
//        for(Task t:tasks)
//            if(t.completed)
//                System.out.println(t.id+" "+t.name+" "+t.deadline+" "+t.priority);
//    }
//    public void getPendings(){
//        System.out.println("Pending Tasks :");
//        for(Task t:tasks)
//            if(!t.completed)
//                System.out.println(t.id+" "+t.name+" "+t.deadline+" "+t.priority);
//    }
}
