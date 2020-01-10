package main;

import main.model.Task;
import main.model.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ToDoListController
{
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/")
    public List<Task> list()
    {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        taskIterable.forEach(t -> tasks.add(t));
        return tasks;
    }

    @PostMapping("/tasks/")
    public int add(Task task)
    {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

   @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id)
    {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(!optionalTask.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity update(@PathVariable int id, Task newTask){
        Task task;
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(!optionalTask.isPresent()){
            task = taskRepository.save(newTask);
            return ResponseEntity.status(HttpStatus.CREATED).body(task.getId());
        } else {
            task = taskRepository.save(newTask);
            return ResponseEntity.status(HttpStatus.OK).body(task.getId());
        }
    }


    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id){
        taskRepository.deleteById(id);
    }




}
