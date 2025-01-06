package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        if (priority < 0 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 0 and 5");
        }
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public void updateDetails(String newTitle, String newDescription) {
        this.title = newTitle;
        this.description = newDescription;
    }

    public void increasePriority() {
        if (priority < 5) {
            priority++;
        }
    }

    public void decreasePriority() {
        if (priority > 0) {
            priority--;
        }
    }

    public boolean isUrgent() {
        return priority >= 4;
    }

    public boolean isLowPriority() {
        return priority <= 1;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }
}