package quiz;

import java.util.Scanner;
import main.ControllerInterface;
import main.MainController;

public class QuizController implements ControllerInterface {
    Scanner sc;
    QuizService quizService = new QuizService();

    @Override
    public void execute(Scanner sc) {
        this.sc = sc;

        // 1. 로그인 체크
        if (MainController.loginUser == null) {
            QuizView.print("로그인이 필요한 서비스입니다.");
            QuizView.print("퀴즈를 맞히고 투자금 10만원을 받아가세요!");
            return;
        }
        String userId = MainController.loginUser.getUserId();

        // 2. 중복 참여 체크
        if (!quizService.canSolveQuiz(userId)) {
            QuizView.print("오늘은 이미 퀴즈에 참여하셨습니다. 내일 또 오세요! 🐿️");
            return;
        }

        // 3. 문제 출제
        QuizDTO quiz = quizService.getQuiz();
        if (quiz == null) {
            QuizView.print("준비된 퀴즈가 없습니다.");
            return;
        }

        QuizView.printQuiz(quiz);

        // 4. 입력 및 결과 처리
        String input = sc.next();
        String resultMsg = quizService.submitAnswer(userId, quiz.getQuizId(), input, quiz.getAnswer());
        QuizView.print(resultMsg);
    }
}