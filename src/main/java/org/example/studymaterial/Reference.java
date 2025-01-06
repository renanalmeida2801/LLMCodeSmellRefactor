package org.example.studymaterial;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private ReferenceStatistics statistics;

    public Reference() {
        this.title = title;
        this.description = description;
        this.link = link;
        this.statistics = new ReferenceStatistics();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ReferenceStatistics getStatistics() {
        return statistics;
    }

    // Delegate view count updates to ReferenceStatistics
    public void incrementViewCount() {
        statistics.incrementViewCount();
    }

    // Delegate download count updates to ReferenceStatistics
    public void incrementDownloadCount() {
        statistics.incrementDownloadCount();
    }

    // Delegate share count updates to ReferenceStatistics
    public void incrementShareCount() {
        statistics.incrementShareCount();
    }

    // High-level methods for derived behavior (examples)
    public boolean isHighlyRated() {
        return getRating() >= 4; // Customize rating threshold as needed
    }

    // Methods preserved for compatibility with AudioReference
    public int getViewCount() {
        return statistics.getViewCount();
    }

    public void setViewCount(int viewCount) {
        statistics.viewCount = viewCount;
    }

    public int getShareCount() {
        return statistics.getShareCount();
    }

    public void setShareCount(int shareCount) {
        statistics.shareCount = shareCount;
    }
}

class ReferenceStatistics {
    public int viewCount;
    public int shareCount;
    private int downloadCount;

    public void incrementViewCount() {
        viewCount++;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void incrementDownloadCount() {
        downloadCount++;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void incrementShareCount() {
        shareCount++;
    }

    public int getShareCount() {
        return shareCount;
    }
}