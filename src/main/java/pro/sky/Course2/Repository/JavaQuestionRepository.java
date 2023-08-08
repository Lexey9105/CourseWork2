package pro.sky.Course2.Repository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pro.sky.Course2.Exceptions.AlreadyAddException;
import pro.sky.Course2.Exceptions.EmptyRequestException;
import pro.sky.Course2.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Repository

public class JavaQuestionRepository implements QuestionRepository{

    private final ArrayList<Question> questions;
    private final ArrayList<Question> questionsMath;



    public JavaQuestionRepository() {
        this.questions = new ArrayList<>();
        this.questionsMath=new ArrayList<>();
    }
    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new AlreadyAddException();
        }questions.add(question);creationMathQuest();

        return question;
    }

    @Override
    public Question remove(Question question) {

            if (!questions.contains(question)) {
                throw new AlreadyAddException();
            }questions.remove(question);

        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    public void creationMathQuest(){
        Random random=new Random();
        int i= random.nextInt(4);
        Integer a=random.nextInt(101);
        Integer b=random.nextInt(51);
        Integer c=null;
        String operator="";
        switch(i) {
            case 0:
                operator = "+";
                c=a+b;
                break;
            case 1:
                operator = "-";
                c=a-b;
                break;
            case 2:
                operator = "*";
                c=a*b;
                break;
            case 3:
                operator = "/";
                c=a/b;
                break;
        }
        String query=Integer.toString(a)+operator+Integer.toString(b);
        String answer=Integer.toString(c);
        Question question=new Question(query,answer);
        questionsMath.add(question);}


    public Question getRandomQuestionJava() {
        Random random=new Random();
        int n= random.nextInt(questions.size());

        return questions.get(n);
    }

    public Question getRandomQuestionMath() {
        Random random=new Random();
        int n= random.nextInt(questions.size());
        return questionsMath.get(n);
    }
    public int getSize(){return questions.size()+questionsMath.size();}

}
