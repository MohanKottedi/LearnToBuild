import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TaskManager {
    private int id=0;
    public void add(String name, int priority, LocalDateTime deadline){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("data.csv",true))){
            bw.write(String.format("%d,%s,%d,%s",id++,name,priority,deadline));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error while adding...");
        }
    }
    public void delete(int id) {
        ArrayList<String> tasks=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader("data.csv"))){
            String a;
            while((a=br.readLine())!=null){
                String obj[]=a.split(",");
                if(Integer.compare(Integer.valueOf(obj[0]),id)==0)  continue;
                tasks.add(a);
            }
            copyToCSV(tasks);
        } catch (IOException e) {
            System.out.println("Error while deleting...");
        }
    }
    public void copyToCSV(ArrayList<String> tasks){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("data.csv"))){
            for(String i:tasks){
                bw.write(i);
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println("Error Changing...");
        }
    }
    public void update(int id,String name,int priority,LocalDateTime deadline){
        ArrayList<String> tasks=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader("data.csv"))){
            String a;
            while((a=br.readLine())!=null){
                String obj[]=a.split(",");
                if(obj[0].equals("")) continue;
                if(Integer.compare(Integer.valueOf(obj[0]),id)==0){
                    a=id+","+name+","+priority+","+deadline.toString();
                }
                tasks.add(a);
            }
            copyToCSV(tasks);
        } catch (IOException e) {
            System.out.println("Error while deleting...");
        }
    }
    public void printAll(){
        try(BufferedReader bw=new BufferedReader(new FileReader("data.csv"))){
            String a;
            System.out.println("ID  |  Name  |   Priority   | DeadLine");
            while((a=bw.readLine())!=null){
                System.out.println(a.replace(",","  |  "));
            }
        }catch(IOException e){
            System.out.println("Error while Reading...");
        }
//        System.out.println("Taks there :");
//        Collections.sort(tasks);
//        for(Task t:tasks) System.out.println(t.id+" "+t.name+" "+t.deadline+" "+t.priority);
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
