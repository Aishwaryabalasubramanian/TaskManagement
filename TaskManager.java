package TaskManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskManager {
     private final Map<Integer,TaskContext> tasks = new HashMap<>();
     private final  Set<String> categorySet= new HashSet<>();
     private final Scanner sc =new Scanner(System.in);
     private  int idCounter=0;
    public  void addTask(){
        System.out.print("Enter Task Id:");
        int taskid = sc.nextInt();
        System.out.print("Enter Task Name:");
        sc.nextLine();
        String taskName = sc.nextLine();
        System.out.print("Enter Task  Description:");
        String taskDescription = sc.nextLine();
        Date dueDate=getDate();
        while (dueDate==null){
            dueDate= getDate();
        }
        Priority priority = getPriority();
        List<String> categories=getCategories();
        tasks.put(++idCounter,new TaskContext(taskid,taskName,taskDescription,dueDate,priority,categories));
        System.out.println("\nTask Added successfully");
    }

    private List<String> getCategories() {
        System.out.print("\n Enter Categories (Comma Separated):");
        sc.nextLine();
        String[] categoriesString = sc.nextLine().split(",");
        List<String> categoriesList = new ArrayList<>();
        for(String category : categoriesString){
            category=category.trim().toUpperCase();
            categoriesList.add(category);
            categorySet.add(category);
        }
        return categoriesList;

    }

    private Priority getPriority() {
        System.out.print("Enter Priority (LOW/MEDIUM/HIGH):");
        Priority priority;
        try {
            priority=Priority.valueOf(sc.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.print("Not a valid Option --- defaulting to MEDIUM priority");
            priority=Priority.MEDIUM;
        }
        return priority;

    }

    private Date getDate() {
        System.out.print("Enter Due Date (dd-mm-yyyy):");
        Date dueDate= stringToDate(sc.next());
        if(dueDate != null && dueDate.before(new Date())){
            System.out.print("Please enter Future Date");
            return null;
        }
        return dueDate;

    }

    private Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Enter a valid Date Format (dd-mm-yyyy)");
            return null;
        }


    }

    public void viewTask() {
        for(Map.Entry<Integer,TaskContext> task : tasks.entrySet()){
            TaskContext taskValue = task.getValue();
            System.out.printf("TaskId: %-2d | Task Name: %-10s | Task Description: %-30s | Due Date: %-10s | Priority: %-6s\n",
                    task.getKey(),
                    taskValue.getTaskName(),
                    taskValue.getTaskDescription(),
                    dateToString(taskValue.getDueDate()),
                    taskValue.getPriority()
            );
        }

    }

    private String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
         return dateFormat.format(date);
    }

    public void viewTaskByPriority() {
        Map<Priority,List<TaskContext>> tasksPriorityMap =new HashMap<>();
        for(TaskContext task : tasks.values()){
            Priority priority =task.getPriority();
            if(!tasksPriorityMap.containsKey(priority)){
                tasksPriorityMap.put(priority,new ArrayList<>());
            }
                tasksPriorityMap.get(priority).add(task);

        }
        String priorityFlag= "PriorityFlag";
        viewTask( "HIGH",tasksPriorityMap.get(Priority.HIGH),priorityFlag);
        viewTask( "MEDIUM",tasksPriorityMap.get(Priority.MEDIUM),priorityFlag);
        viewTask( "LOW",tasksPriorityMap.get(Priority.LOW),priorityFlag);

    }
    private void    viewTask(String priority,List<TaskContext> tasks,String flag) {
        if(tasks!=null) {
            if(flag.equals("PriorityFlag")) {
                System.out.print("\n<------The " + priority + " Priority Tasks---->");
            } else if (flag.equals("CategoryFlag")) {
                System.out.print("\n<------The Tasks Under " + priority + " ------>");

            }
            for (TaskContext task : tasks) {
                System.out.printf("\nTaskId: %-2d | Task Name: %-10s | Task Description: %-30s | Due Date: %-10s | Priority: %-6s\n",
                        task.getTaskId(),
                        task.getTaskName(),
                        task.getTaskDescription(),
                        dateToString(task.getDueDate()),
                        task.getPriority()
                );


            }
        }

    }

    public void viewTaskByCategory() {
        Map<String,List<TaskContext>> categoryMap= new HashMap<>();
        for(TaskContext task : tasks.values()){
            for(String category:task.getCategories()){
                if(!categoryMap.containsKey(category)){
                   categoryMap.put(category,new ArrayList<>());
                }
                categoryMap.get(category).add(task);

            }
        }
        for(String category: categorySet){
            viewTask(category,categoryMap.get(category),"CategoryFlag");
        }

    }
}
