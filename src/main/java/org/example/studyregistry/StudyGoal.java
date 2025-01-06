package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudyGoal extends Registry{
    private String goal;
    private List<String> goalRequirements;
    private Boolean isCompleted;
    private LocalDateTime createdDate;
    private Double goalCompletion;
    private StudyObjective studyObjective;
    private StudyPlan studyPlan;
    private String summary;

    public StudyGoal(String name, StudyObjective objective, StudyPlan plan) {
        this.name = name;
        this.studyObjective = objective;
        this.studyPlan = plan;
        goalRequirements = new ArrayList<>();
    }

    public void editActiveCompleted(boolean active, boolean completed){
        this.isActive = active;
        this.isCompleted = completed;
    }

    private String buildActiveGoalSummary() {
        if (this.isActive) {
            return "Active Goal:\n" + goal + "\n\n";
        }
        return "";
    }

    private String buildCompletedGoalSummary() {
        if (this.isCompleted) {
            return "Completed Goal:\n" + goal + "\n\n";
        }
        return "";
    }

    private String buildRequirementsSummary() {
        if (this.goalRequirements != null && !this.goalRequirements.isEmpty()) {
            StringBuilder requirements = new StringBuilder("Requirements:\n");
            for (String requirement : this.goalRequirements) {
                requirements.append(requirement).append(", ");
            }
            // Remove trailing comma
            if (requirements.length() > 11) {
                requirements.setLength(requirements.length() - 2);
            }
            requirements.append("\n");
            return requirements.toString();
        }
        return "";
    }

    private String buildPlanSummary() {
        if (this.studyPlan != null) {
            return "Plan:\n" + this.studyPlan.toString() + "\n";
        }
        return "";
    }

    private String buildObjectiveSummary() {
        if (this.studyObjective != null) {
            return "Objective:\n" + this.studyObjective.toString() + "\n";
        }
        return "";
    }

    private String buildGoalSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append(buildActiveGoalSummary());
        summary.append(buildCompletedGoalSummary());
        summary.append(buildRequirementsSummary());
        summary.append(buildPlanSummary());
        summary.append(buildObjectiveSummary());
        return summary.toString();
    }

    public String setGoalSummary(){
        this.summary = buildGoalSummary();
        return summary;
    }

    public void addRequirement(String requirement){
        this.goalRequirements.add(requirement);
    }

    public void resetRequirements(){
        this.goalRequirements.clear();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void toggleIsCompleted(){
        this.isCompleted = !this.isCompleted;
    }

    public LocalDateTime getLimitDate() {
        return createdDate;
    }

    public void setLimitDate(LocalDateTime limitDate) {
        this.createdDate = limitDate;
    }

    public void addDaysLimitDate(int days){
        this.createdDate = this.createdDate.plusDays(days);
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}