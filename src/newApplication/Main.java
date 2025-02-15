package newApplication;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Questions questions = new Questions();
    private static Answers answers = new Answers();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Ask a question");
            System.out.println("2. View questions");
            System.out.println("3. Answer a question");
            System.out.println("4. Search questions");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createQuestion();
                    break;
                case 2:
                    viewAllQuestions();
                    break;
                case 3:
                    addAnswer();
                    break;
                case 4:
                    searchQuestions();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid!");
            }
        }
        scanner.close();
    }

    private static void createQuestion() {
        
        System.out.print("Enter question title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter question: ");
        String content = scanner.nextLine();

        String validationError = Question.validateQuestion(title, content);
        if (validationError.isEmpty()) {
            Question question = new Question(title, content);
            questions.addQuestion(question);
            System.out.println("Question created successfully!\n");
            
            List<Question> display = questions.findSimilarQuestions(title, content);
            if (!display.isEmpty()) {
                System.out.println("Question created:");
                for (Question q : display) {
                    System.out.println("- " + q.getTitle() + "\n");
                }
            }
        } else {
            System.out.println("Error: " + validationError);
        }
    }

    private static void viewAllQuestions() {
        List<Question> allQuestions = questions.getAllQuestions();
        if (allQuestions.isEmpty()) {
            System.out.println("No questions found.");
            return;
        }

        for (int i = 0; i < allQuestions.size(); i++) {
            Question q = allQuestions.get(i);
            System.out.println("\n" + (i + 1) + ". " + q.getTitle());
            System.out.println("   Content: " + q.getContent());
            
            // Display answers for this question
            List<Answer> questionAnswers = answers.getAllAnswers();
            if (!questionAnswers.isEmpty()) {
                System.out.println("   Answers:");
                for (Answer a : questionAnswers) {
                    System.out.println("   - " + a.getContent());
                }
            } else {
                System.out.println("   No answers yet\n");
            }
        }
    }

    private static void addAnswer() {
        List<Question> allQuestions = questions.getAllQuestions();
        if (allQuestions.isEmpty()) {
            System.out.println("No questions available to answer.");
            return;
        }

        System.out.println("\nEnter the question you want to answer:");
        for (int i = 0; i < allQuestions.size(); i++) {
            System.out.println((i + 1) + ". " + allQuestions.get(i).getTitle());
        }

        System.out.print("\nEnter question number: ");
        int questionNumber;
        try {
            questionNumber = Integer.parseInt(scanner.nextLine());
            if (questionNumber < 1 || questionNumber > allQuestions.size()) {
                System.out.println("Invalid question number!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return;
        }

        Question selectedQuestion = allQuestions.get(questionNumber - 1);
        System.out.println("\nSelected Question:");
        System.out.println("Title: " + selectedQuestion.getTitle());
        System.out.println("Content: " + selectedQuestion.getContent());
        
        System.out.print("\nEnter your answer: ");
        String content = scanner.nextLine();

        String validationError = Answer.validateAnswer(content);
        if (validationError.isEmpty()) {
            Answer answer = new Answer(content);
            answers.addAnswer(answer);
            System.out.println("Answer added successfully!");
        } else {
            System.out.println("Error: " + validationError);
        }
    }

    private static void searchQuestions() {
        System.out.print("\nWhat do you want to search?: ");
        String searchTerm = scanner.nextLine();

        List<Question> searchResults = questions.searchQuestions(searchTerm);
        if (searchResults.isEmpty()) {
            System.out.println("No matching questions found.");
            return;
        }

        for (Question q : searchResults) {
            System.out.println("\nTitle: " + q.getTitle());
            System.out.println("Content: " + q.getContent());
        }
    }
}