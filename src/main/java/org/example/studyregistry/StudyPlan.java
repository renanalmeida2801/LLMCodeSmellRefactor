package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlan extends Registry{
    private StudyObjective objective;
    private List<String> steps;

    public StudyPlan(String planName, StudyObjective objective, List<StudyMaterial> materials) {
        this.name = planName;
        this.objective = objective;
        this.steps = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "Plan: " + name + ",\nObjective: " + objective.getDescription() + ",\nSteps: " + String.join(", ", steps);
    }

    public List<String> getSteps() {
        return steps;
    }

    public StudyObjective getObjective() {
        return objective;
    }

    public void assignObjective(StudyObjective objective) {
        this.objective = objective;
    }

    public void addSingleStep(String toAdd){
        steps.add(toAdd);
    }

    public record StudyPlanDetails(
            String firstStep,
            String resetStudyMechanism,
            String consistentStep,
            String seasonalSteps,
            String basicSteps,
            String mainObjectiveTitle,
            String mainGoalTitle,
            String mainMaterialTopic,
            String mainTask,
            Integer numberOfSteps,
            boolean isImportant,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {}

    public void assignSteps(StudyPlanDetails details) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.steps = new ArrayList<>(Arrays.asList(
                details.firstStep,
                details.resetStudyMechanism,
                details.consistentStep,
                details.seasonalSteps,
                details.basicSteps,
                "Number of steps: " + details.numberOfSteps.toString(),
                "Is it important to you? " + details.isImportant,
                details.startDate.format(formatter),
                details.endDate.format(formatter),
                details.mainObjectiveTitle,
                details.mainGoalTitle,
                details.mainMaterialTopic,
                details.mainTask
        ));
    }

    public void handleAssignSteps(List<String> stringProperties, Integer numberOfSteps,
                                  boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
        // Create an instance of StudyPlanDetails
        StudyPlanDetails details = new StudyPlanDetails(
                stringProperties.get(0),
                stringProperties.get(1),
                stringProperties.get(2),
                stringProperties.get(3),
                stringProperties.get(4),
                stringProperties.get(5),
                stringProperties.get(6),
                stringProperties.get(7),
                stringProperties.get(8),
                numberOfSteps,
                isImportant,
                startDate,
                endDate
        );
        assignSteps(details);
    }
}
