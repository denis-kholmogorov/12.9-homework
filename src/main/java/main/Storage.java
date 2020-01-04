package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage
{
    private static int currentId = 1;
    private static HashMap<Integer, Task> tasks = new HashMap<>();

    public static List<Task> getAllTasks(){
        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.addAll(tasks.values());
        return tasksList;
    }

    public static int addTask(Task task)
    {
        int id = currentId++;
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public static Task getTask(int taskId){
        if(tasks.containsKey(taskId)){
            return tasks.get(taskId);
        }
        return null;
    }

    public static boolean deleteTask(int taskId){
        if(tasks.containsKey(taskId)){
            tasks.remove(taskId);
            return true;
        }
        return false;

    }
}
