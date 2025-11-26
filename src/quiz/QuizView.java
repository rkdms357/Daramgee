package quiz;

public class QuizView {

    public static void printQuiz(QuizDTO quiz) {
        System.out.println("=============오늘의 투자 퀴즈=============");
        System.out.println("Q. " + quiz.getQuestion());
        System.out.println("--------------------------------------");

        // 보기 배열 출력
        String[] options = quiz.getOptions();
        if (options != null) {
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
        }
        System.out.println("--------------------------------------");
        System.out.print("정답 번호 입력>> ");
    }

    public static void print(String msg) {
        System.out.println(msg);
    }
}