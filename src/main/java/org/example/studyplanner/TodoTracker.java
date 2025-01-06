package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TodoTracker {
    private List<ToDo> toDos = new ArrayList<>();
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;
    private static TodoTracker instance;

    private TodoTracker() {
        this.tracker = new HashMap<>();
        this.toDos = new ArrayList<>();
        this.nextId = 1;
    }

    public static TodoTracker getInstance() {
        if (instance == null) {
            instance = new TodoTracker();
        }
        return instance;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (ToDo toDo : toDos) {
            str.append(formatToDo(toDo));
            str.append("\n");
        }
        String response = str.toString();
        if (response.isEmpty()) {
            return "No ToDos found";
        }
        return response;
    }

    private String formatToDo(ToDo toDo) {
        StringBuilder str = new StringBuilder();
        str.append(toDo.toString());
        str.append("\n");
        str.append(formatTracks(toDo));
        return str.toString();
    }

    private String formatTracks(ToDo toDo) {
        StringBuilder str = new StringBuilder();
        List<LocalDateTime> todosDate = this.tracker.get(toDo.getId());
        if (todosDate == null) {
            str.append("No tracks found\n");
        } else {
            for (LocalDateTime ldt : todosDate) {
                str.append(formatDate(ldt));
                str.append("\n");
            }
        }
        return str.toString();
    }

    private String formatDate(LocalDateTime ldt) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(ldt);
    }

    public void addToDoExecutionTime(Integer id){
        trackToDoExecutionTime(id);
    }

    private void trackToDoExecutionTime(Integer id) {
        List<LocalDateTime> et = tracker.computeIfAbsent(id, k -> new ArrayList<>());
        LocalDateTime now = LocalDateTime.now();
        et.add(now);
    }

    public List<ToDo> getToDos() {
        return toDos;
    }

    public ToDo getToDoById(Integer id) {
        for (ToDo toDo : toDos) {
            if (toDo.getId() == id) {
                return toDo;
            }
        }
        return null;
    }

    public Integer addToDo(String title, String description, Integer priority) {
        ToDo toAdd = new ToDo(nextId, title, description, priority);
        nextId++;
        this.toDos.add(toAdd);
        return toAdd.getId();
    }

    public void removeToDo(Integer id) {
        toDos.removeIf(toDo -> toDo.getId() == id);
    }

    public List<ToDo> sortTodosByPriority() {
        List<ToDo> sortedToDos = new ArrayList<>(toDos);
        sortedToDos.sort(Comparator.comparingInt(ToDo::getPriority));
        return sortedToDos;
    }

    public List<String> searchInTodos(String search) {
        List<String> todos = new ArrayList<>();
        for (ToDo toDo : toDos) {
            if (toDo.getTitle().toLowerCase().contains(search.toLowerCase()) || toDo.getDescription().toLowerCase().contains(search.toLowerCase())) {
                todos.add(toDo.toString());
            }
        }
        return todos;
    }
}