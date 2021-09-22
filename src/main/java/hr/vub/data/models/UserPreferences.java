package hr.vub.data.models;

public class UserPreferences {

    public static UserPreferences empty() {
        return new UserPreferences(false);
    }

    private final boolean didGenerateInitialData;

    public UserPreferences(boolean didGenerateInitialData) {
        this.didGenerateInitialData = didGenerateInitialData;
    }

    public boolean didGenerateInitialData() {
        return didGenerateInitialData;
    }
}
