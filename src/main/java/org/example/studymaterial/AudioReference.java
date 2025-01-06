package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }

    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality) {
        super();
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality) {
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

    // Refactored editAudio method with fewer parameters
    public void editAudio(AudioQuality audioQuality, boolean isDownloadable, AudioReferenceData data) {
        this.setAudioQuality(audioQuality);
        this.setDownloadable(isDownloadable);
        setTitleAndDescription(data.title(), data.description());
        setLinkAndAccessRights(data.link(), data.accessRights());
        setMediaDetails(data.license(), data.language(), data.rating(), data.viewCount(), data.shareCount());
    }

    private void setTitleAndDescription(String title, String description) {
        this.setTitle(title);
        this.setDescription(description);
    }

    private void setLinkAndAccessRights(String link, String accessRights) {
        this.setLink(link);
        this.setAccessRights(accessRights);
    }

    private void setMediaDetails(String license, String language, int rating, int viewCount, int shareCount) {
        this.setLicense(license);
        this.setLanguage(language);
        this.setRating(rating);
        this.setViewCount(viewCount);
        this.setShareCount(shareCount);
    }

    public record AudioReferenceData(
            String title,
            String description,
            String link,
            String accessRights,
            String license,
            String language,
            int rating,
            int viewCount,
            int shareCount
    ) {
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        AudioReferenceData data = new AudioReferenceData(
                properties.get(0), // title
                properties.get(1), // description
                properties.get(2), // link
                properties.get(3), // accessRights
                properties.get(4), // license
                properties.get(5), // language
                intProperties.get(0), // rating
                intProperties.get(1), // viewCount
                intProperties.get(2) // shareCount
        );
        this.editAudio(audioQuality, isDownloadable, data);
    }

    // Removed editVideoAttributes method and replaced with setBasicAttributes

    private void setBasicAttributes(int rating, String language, int viewCount, int shareCount) {
        this.setRating(rating);
        this.setShareCount(shareCount);
        this.setViewCount(viewCount);
        this.setLanguage(language);
    }

    public void editBasic(String title, String description, String link){
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }
}