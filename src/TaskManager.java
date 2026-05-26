import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TaskManager {
    private int nextId=0;
    ArrayList<Task> tasks;
    DateTimeFormatter frmt=DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
    public void loadTasks(String path){
        tasks=new ArrayList<>();
        try(BufferedReader bw=new BufferedReader(new FileReader(path))){
            String a=bw.readLine();
            while((a=bw.readLine())!=null){
                String para[]=a.split(",");
                if(para.length!=4) continue;
                int id=Integer.parseInt(para[0]);
                tasks.add(Task.builder()
                        .id(id)
                        .name(para[1])
                        .priority(Integer.parseInt(para[2]))
                        .deadline(LocalDateTime.parse(para[3]))
                        .build());
                nextId=nextId<id?id:nextId;
            }
            System.out.println("Loaded Sucessfulyy...");
        }catch(IOException e){
            System.out.println("Error while loading csv file...");
        }
    }
    public void saveTasks(String path){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(path))){
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
        loadTasks("data.csv");
       tasks.add(Task.builder()
               .id(++nextId)
               .name(name)
               .priority(priority)
               .deadline(deadline)
               .build());
       saveTasks("data.csv");
    }
    public void delete(int id) {
        loadTasks("data.csv");
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).id==id) {tasks.remove(i);break;}
        }
        saveTasks("data.csv");
    }
    public void update(int id,String name,int priority,LocalDateTime deadline){
        loadTasks("data.csv");
        for(Task task:tasks){
            if(task.id==id){
                task.name=name;
                task.priority=priority;
                task.deadline=deadline;
                break;
            }
        }
        saveTasks("data.csv");
    }
    public void printAll(){
        loadTasks("data.csv");
        Collections.sort(tasks);
        System.out.println("Pending Tasks");
        for(Task task:tasks){
            System.out.printf("%d  |  %s  |  %d  |  %s\n"
                    ,task.id,task.name,task.priority,task.deadline.format(frmt));
        }
        loadTasks("history.csv");
        System.out.println("Completed Tasks");
        for(Task task:tasks){
            System.out.printf("%d  |  %s  |  %d  |  %s\n"
                    ,task.id,task.name,task.priority,task.deadline.format(frmt));
        }
    }

    public void markCompleted(int id){
        loadTasks("data.csv");
        Task complete=null;
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).id==id) {complete=tasks.remove(i);break;}
        }
        loadTasks("history.csv");
        tasks.add(complete);
        saveTasks("history.csv");
    }
    public void getCompleted(){
        loadTasks("history.csv");
        printAll();
    }
    public void getPendings(){
        loadTasks("data.csv");
        Collections.sort(tasks);
        System.out.println("Pending Tasks");
        for(Task task:tasks){
            System.out.printf("%d  |  %s  |  %d  |  %s\n"
                    ,task.id,task.name,task.priority,task.deadline.format(frmt));
        }
    }
}
