package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HabitTracker {
    private List<Habit> habits;
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;

    private static HabitTracker instance;

    public static HabitTracker getHabitTracker() {
        if (instance == null) {
            instance = new HabitTracker();
        }
        return instance;
    }

    private HabitTracker() {
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(habit.toString()).append(", ");
        }
        return "Habits: " + response.toString();
    }

    public Habit getHabitById(Integer id) {
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Habit> getHabits() {
        return this.habits;
    }

    public String formatHabitDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }

    public List<Integer> getTrackerKeys() {
        return new ArrayList<>(this.tracker.keySet());
    }

    public int addHabit(String name, String motivation) {
        Habit habit = new HabitBuilder()
                .setName(name)
                .setMotivation(motivation)
                .setId(this.nextId)
                .build();
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public int addHabit(HabitBuilder builder) {
        Habit habit = builder.setId(this.nextId).build();
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded) {
        HabitBuilder builder = new HabitBuilder()
                .setName(stringProperties.get(0))
                .setMotivation(stringProperties.get(1))
                .setDailyMinutesDedication(intProperties.get(0))
                .setDailyHoursDedication(intProperties.get(1))
                .setStartDate(LocalDateTime.of(
                        intProperties.get(2), intProperties.get(3), intProperties.get(4),
                        intProperties.get(5), intProperties.get(6), intProperties.get(7)
                ))
                .setIsConcluded(isConcluded);
        return addHabit(builder);
    }

    public void addHabitRecord(Integer id) {
        tracker.get(id).add(LocalDateTime.now());
    }

    public void toggleConcludeHabit(Integer id) {
        for (Habit habit : this.habits) {
            if (habit.getId().equals(id)) {
                habit.setIsConcluded(!habit.getIsConcluded());
            }
        }
    }

    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.get(id);
    }

    public List<String> searchInHabits(String search) {
        List<String> habits = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) ||
                    habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                habits.add(habit.toString());
            }
        }
        return habits;
    }

    public String habitDateViewAll() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append("[ Habit: ")
                    .append(habit.getName())
                    .append(". Records: ");
            List<LocalDateTime> records = getHabitRecords(habit.getId());
            for (LocalDateTime record : records) {
                response.append(formatHabitDate(record)).append(", ");
            }
            response.append("]");
        }
        return response.toString();
    }

    // Inner class: HabitBuilder
    public static class HabitBuilder {
        private String name;
        private String motivation;
        private Integer dailyMinutesDedication = 0;
        private Integer dailyHoursDedication = 0;
        private LocalDateTime startDate = LocalDateTime.now();
        private Boolean isConcluded = false;
        private Integer id;

        public HabitBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public HabitBuilder setMotivation(String motivation) {
            this.motivation = motivation;
            return this;
        }

        public HabitBuilder setDailyMinutesDedication(Integer dailyMinutesDedication) {
            this.dailyMinutesDedication = dailyMinutesDedication;
            return this;
        }

        public HabitBuilder setDailyHoursDedication(Integer dailyHoursDedication) {
            this.dailyHoursDedication = dailyHoursDedication;
            return this;
        }

        public HabitBuilder setStartDate(LocalDateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public HabitBuilder setIsConcluded(Boolean isConcluded) {
            this.isConcluded = isConcluded;
            return this;
        }

        public HabitBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Habit build() {
            LocalTime dedicationTime = LocalTime.of(dailyHoursDedication, dailyMinutesDedication);
            return new Habit(name, motivation, dedicationTime, id, startDate, isConcluded);
        }
    }
}