import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TaskManager {
    private static int nextId=0;
    private static ArrayList<Task> tasks;
    private final static String data="data.csv";
    private final static String history="history.csv";
    private static DateTimeFormatter frmt=DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    public TaskManager(){
        loadTasks(data);
    }

    private static void loadTasks(String path){
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
                nextId=Math.max(nextId,id);
            }
            System.out.println("Loaded Sucessfulyy...");
        }catch(IOException e){
            System.out.println("Error while loading csv file...");
        }
    }
    private static void saveTasks(String path){
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
    public static void add(String name, int priority, LocalDateTime deadline){
       tasks.add(Task.builder()
               .id(++nextId)
               .name(name)
               .priority(priority)
               .deadline(deadline)
               .build());
       saveTasks(data);
    }
    public static void delete(int id) {
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).id==id) {tasks.remove(i);break;}
        }
        saveTasks(data);
    }
    public static void update(int id,String name,int priority,LocalDateTime deadline){
        for(Task task:tasks){
            if(task.id==id){
                task.name=name;
                task.priority=priority;
                task.deadline=deadline;
                break;
            }
        }
        saveTasks(data);
    }
    public static void markCompleted(int id){
        Task complete=null;
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).id==id) {complete=tasks.remove(i);break;}
        }
        saveTasks(data);
        loadTasks(history);
        tasks.add(complete);
        saveTasks(history);
        loadTasks(data);
    }

    private static void print(String name){
        Collections.sort(tasks);
        System.out.println(name);
        System.out.println("ID  |  NAME  |  Priority  |  DeadLine");
        for(Task task:tasks){
            System.out.printf("%d  |  %s  |  %d  |  %s\n"
                    ,task.id,task.name,task.priority,task.deadline.format(frmt));
        }
    }
    public void getCompleted(){
        loadTasks(history);
        print("Completed Tasks");
        loadTasks(data);
    }
    public void getPendings(){
        print("Pending Tasks");
    }
    public void printAll(){
        getPendings();
        getCompleted();
    }
}
