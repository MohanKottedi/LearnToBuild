//import java.io.*;
//
//public class learn {
//    public static void main(String[] args) {
//        try(BufferedWriter bw=new BufferedWriter(new FileWriter(data))){
//            bw.write("name,age,gender");
//            bw.newLine();
//            bw.write("mohan,18,male");
//        }
//        catch (IOException e){
//            System.out.println("not worked");
//        }
//
//        try (BufferedReader br=new BufferedReader(new FileReader(data))){
//            String a;
//            while((a=br.readLine())!=null){
//                System.out.println(a);
//            }
//        }
//        catch (IOException e){
//            System.out.println("not worked");
//        }
//    }
//}
