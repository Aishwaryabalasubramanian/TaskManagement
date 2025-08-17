package TaskManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nWelcome to Task Management Application");
        TaskManager taskManager= new TaskManager();
        boolean running = true;
        while(running){
            System.out.println("\nSelect options \n1.Add Task \n2.View Task \n3.View Task By Priority  \n4.View Task By Category\n0.Exit");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    taskManager.addTask();
                    break;
                case 2:
                    taskManager.viewTask();
                    break;
                case 3:
                    taskManager.viewTaskByPriority();
                    break;
                case 4:
                    taskManager.viewTaskByCategory();
                    break;
                case 0:
                    running=false;
                    sc.close();
                    break;
            }

        }
    }
}
