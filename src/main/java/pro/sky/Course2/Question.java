package pro.sky.Course2;

import java.util.Objects;

public class Question {
     private String query;
     private String answer;

     public Question(String query, String answer){
         this.query =query;
         this.answer=answer;
     }

    public String getQuery() {
        return query;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(query, question1.query) && Objects.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, answer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "query='" + query + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
