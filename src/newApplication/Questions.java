package newApplication;

import java.util.ArrayList;
import java.util.List;

public class Questions {
    private List<Question> questions;

    public Questions() {
        this.questions = new ArrayList<>();
    }

    // Create
    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    public List<Question> getUnresolvedQuestions() {
        List<Question> unresolved = new ArrayList<>();
        for (Question q : questions) {
            if (!q.isResolved()) {
                unresolved.add(q);
            }
        }
        return unresolved;
    }

    public List<Question> searchQuestions(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        List<Question> results = new ArrayList<>();
        for (Question q : questions) {
            if (q.getTitle().toLowerCase().contains(searchTerm) ||
                q.getContent().toLowerCase().contains(searchTerm)) {
                results.add(q);
            }
        }
        return results;
    }

    // Update question at specific index
    public boolean updateQuestion(int index, String title, String content) {
        if (index >= 0 && index < questions.size()) {
            String validationError = Question.validateQuestion(title, content);
            if (validationError.isEmpty()) {
                Question question = questions.get(index);
                question.setTitle(title);
                question.setContent(content);
                return true;
            }
        }
        return false;
    }

    // Delete question at specific index
    public boolean removeQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
            return true;
        }
        return false;
    }

    // Find similar questions
    public List<Question> findSimilarQuestions(String title, String content) {
        String searchText = (title + " " + content).toLowerCase();
        List<Question> similar = new ArrayList<>();
        for (Question q : questions) {
            String questionText = (q.getTitle() + " " + q.getContent()).toLowerCase();
            if (questionText.contains(searchText) || searchText.contains(questionText)) {
                similar.add(q);
            }
        }
        return similar;
    }
}