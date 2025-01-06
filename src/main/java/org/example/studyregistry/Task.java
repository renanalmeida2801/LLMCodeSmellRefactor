package org.example.studyregistry;

import java.time.LocalDateTime;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        this.title = title;
        this.name = title; // Set name during initialization
        this.description = description;
        this.author = author;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
        this.name = newTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void assignAuthor(String newAuthor) {
        this.author = newAuthor;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void reschedule(LocalDateTime newDate) {
        if (newDate == null || newDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("New date cannot be null or in the past");
        }
        this.date = newDate;
    }

    public boolean isOverdue() {
        return LocalDateTime.now().isAfter(date);
    }

    public long daysUntilDue() {
        return java.time.Duration.between(LocalDateTime.now(), date).toDays();
    }

    public String getDisplayName() {
        return title;
    }
}