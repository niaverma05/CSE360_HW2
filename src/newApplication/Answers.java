package newApplication;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    // Create
    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAllAnswers() {
        return new ArrayList<>(answers);
    }

    public List<Answer> searchAnswers(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Answer> results = new ArrayList<>();
        for (Answer a : answers) {
            if (a.getContent().toLowerCase().contains(searchTerm)) {
                results.add(a);
            }
        }
        return results;
    }

    // Update answer at specific index
    public boolean updateAnswer(int index, String content) {
        if (index >= 0 && index < answers.size()) {
            String validationError = Answer.validateAnswer(content);
            if (validationError.isEmpty()) {
                Answer answer = answers.get(index);
                answer.setContent(content);
                return true;
            }
        }
        return false;
    }

    // Delete answer at specific index
    public boolean removeAnswer(int index) {
        if (index >= 0 && index < answers.size()) {
            answers.remove(index);
            return true;
        }
        return false;
    }
}