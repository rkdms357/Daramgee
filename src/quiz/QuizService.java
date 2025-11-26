package quiz;

public class QuizService {
    QuizDAO quizDAO = new QuizDAO();

    // 오늘 풀었는지 체크
    public boolean canSolveQuiz(String userId) {
        return !quizDAO.hasSolvedToday(userId); // 안 풀었으면 true
    }

    // 문제 가져오기
    public QuizDTO getQuiz() {
        return quizDAO.getRandomQuiz();
    }

    // 정답 제출 및 결과 처리
    public String submitAnswer(String userId, int quizId, String userAnswer, String realAnswer) {
        String correctYn = "N";
        String msg = "";

        if (userAnswer.equals(realAnswer)) {
            correctYn = "Y";
            quizDAO.giveReward(userId, 100000); // 10만원 지급!
            msg = "🎉정답입니다! 투자 지원금 100,000원이 입금되었습니다.";
        } else {
            correctYn = "N";
            msg = "땡! 오답입니다. (정답: " + realAnswer + "번)";
        }

        // 기록 남기기
        quizDAO.insertLog(userId, quizId, correctYn);
        return msg;
    }
}