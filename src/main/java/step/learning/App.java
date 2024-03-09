package step.learning;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class App {
    public static void main(String[] args) {
        displayMenu();
    }

    public static void callContact(String name){
        System.out.println ("Дзвінок до " + name);
    }

    public static void saveContact(String name, long number)
    {
        System.out.println ("Зберегти контакт " + name + " : " + number);
//        try {
//            File file = new File("file.txt");
//            if (!file.exists())
//                file.createNewFile();
//            PrintWriter pw = new PrintWriter(new FileWriter(file,true));
//            pw.println(name + ":" + number);
//            pw.close();
//        }
//        catch (IOException e){
//            System.out.println (e.getMessage());
//        }
        try (PrintWriter pw = new PrintWriter(new FileWriter("file.txt",true))){
            pw.println(name + ":" + number);
        }
        catch (IOException e){
            System.out.println (e.getMessage());
        }
    }
    public static void findNumber(String name){

        try (Scanner in =new Scanner( new File("file.txt"))){
            String s [];

            boolean foundPerson = false;

            while(in.hasNextLine()) {
                s = in.nextLine().split(":");
                if(s[0].equals(name)){
                    System.out.println ("Номер " + s[1]);
                }
            }
        }catch (IOException e){
            System.out.println (e.getMessage());
        }

        System.out.println ("належить контакту " + name);
    }

    public static void displayMenu(){
       try (Scanner in = new Scanner(System.in)) {
           String name;

           System.out.println("ТЕЛЕФОННИЙ ДОВІДНИК");

           System.out.println("ОБЕРІТЬ ВАРІАНТ");
           System.out.println("1 - Обрати контакт");
           System.out.println("2 - Зберегти контакт");
           System.out.println("3 - Пошук контакту");

           //Break this later
           int choice = in.nextInt();
           in.nextLine();

           switch (choice) {
               case 1://Кому б ви хотіли подзвонити? (Ім'я прізвище)
                   System.out.println("\nВведіть прізвище та ім'я контакту");
                   name = in.nextLine();

                   callContact(name);
                   break;
               case 2:
                   System.out.println("\nЗБЕРЕГТИ КОНТАКТ");
                   System.out.println("\nВведіть ім'я та прізвище");
                   name = in.nextLine();


                   System.out.println("\nВведіть номер телефону");
                   long number = in.nextLong();
                   in.nextLine();

                   saveContact(name, number);
                   break;
               case 3:
                   System.out.println("ПОШУК");
                   System.out.println("\nВведіть прізвище та ім'я для пошуку");
                   findNumber(in.nextLine());

                   break;

               default:
                   System.out.println("INVALID");
                   break;
           }
       }catch(Exception e){
           System.out.println(e.getMessage());
       }

    }
}


