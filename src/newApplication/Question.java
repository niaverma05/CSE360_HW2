package newApplication;

public class Question {
    private String title;
    private String content;
    private boolean resolved;

    public Question(String title, String content) {
        this.title = title;
        this.content = content;
        this.resolved = false;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }
    
    // Input validation
    public static String validateQuestion(String title, String content) {
        if (title == null || title.trim().isEmpty()) {
            return "Title cannot be empty";
        }
        if (title.length() > 200) {
            return "Title cannot be longer than 200 characters";
        }
        if (content == null || content.trim().isEmpty()) {
            return "Content cannot be empty";
        }
        if (content.length() > 5000) {
            return "Content cannot be longer than 5000 characters";
        }
        return ""; // Empty string means no validation errors
    }
    
    @Override
    public String toString() {
        return title;
    }
}