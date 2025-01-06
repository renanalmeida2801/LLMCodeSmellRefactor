package org.example.studysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private List<String> searchHistory;
    private Map<String, Integer> searchCount;
    private boolean isLocked;
    private Integer numUsages;
    private String logName;

    public SearchLog(String logName) {
        this.logName = logName;
        reset();
    }

    private void reset() {
        searchHistory = new ArrayList<>();
        searchCount = new HashMap<>();
        numUsages = 0;
        isLocked = false;
    }

    public void addSearch(String query) {
        if (isLocked) {
            throw new IllegalStateException("SearchLog is locked.");
        }

        searchHistory.add(query);
        searchCount.put(query, searchCount.getOrDefault(query, 0) + 1);
        numUsages++;
    }

    public void logSearch(String query, List<String> results) {
        if (isLocked) {
            throw new IllegalStateException("SearchLog is locked.");
        }

        searchHistory.add(query);
        searchCount.put(query, searchCount.getOrDefault(query, 0) + 1);
        numUsages++;
        results.add("\nLogged in: " + logName);
    }

    public void addSearchHistory(String query) {
        // Maintain compatibility with legacy tests
        searchHistory.add(query);
        searchCount.put(query, searchCount.getOrDefault(query, 0) + 1);
    }

    public List<String> getSearchHistory() {
        return new ArrayList<>(searchHistory); // Return a copy to prevent modifications
    }

    public Map<String, Integer> getSearchCount() {
        return new HashMap<>(searchCount); // Return a copy to prevent modifications
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }

    public Integer getNumUsages() {
        return numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void setNumUsages(int numUsages) {
        this.numUsages = numUsages;
    }
}