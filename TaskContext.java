package TaskManagement;

import java.util.Date;
import java.util.List;

public class TaskContext {
    private final int taskId;
    private String taskName;
    private String taskDescription;
    private Date dueDate;
    private Priority priority;

    public List<String> getCategories() {
        return categories;
    }

    private List<String> categories;

    public String getTaskName() {
        return taskName;
    }

    public Priority getPriority() {
        return priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public TaskContext(int taskId, String taskName, String taskDescription, Date dueDate,Priority priority,List<String> categories) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.taskDescription=taskDescription;
        this.dueDate=dueDate;
        this.priority=priority;
        this.categories=categories;
    }
}
