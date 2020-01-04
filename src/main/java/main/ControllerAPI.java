package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import main.model.Task;
import main.model.TaskRepository;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class ControllerAPI {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/api/tasks/")
    public String jsonTasks(){
        Iterable<Task> taskIterable = taskRepository.findAll();
        Gson gson = new Gson();
        ObjectMapper mapper = new ObjectMapper();

        JSONArray jsonData = new JSONArray();
        taskIterable.forEach(t ->{
            String jsonTask = gson.toJson(t);
            jsonData.add(jsonTask);
        });


        return jsonData.toString();
    }

}
