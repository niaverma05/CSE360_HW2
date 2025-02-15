package newApplication;

public class Answer {
    private String content;

    public Answer(String content) {
        this.content = content;
    }

    // Getters and setters
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    // Input validation
    public static String validateAnswer(String content) {
        if (content == null || content.trim().isEmpty()) {
            return "Answer content cannot be empty";
        }
        if (content.length() > 5000) {
            return "Answer content cannot be longer than 5000 characters";
        }
        return ""; // Empty string means no validation errors
    }
    
    @Override
    public String toString() {
        return content;
    }
}