/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.sistemas.moduloevaluacionestudiantes.Management;

/**
 *
 * @author shampussy
 */
public class Answers {
    private String category,positionOfQuestion,answerOfStudent,question;

    public Answers(String category, String positionOfQuestion, String answerOfStudent) {
        this.category = category;
        this.positionOfQuestion = positionOfQuestion;
        this.answerOfStudent = answerOfStudent;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answers() {
    }

    
    public String getCategory() {
        return category;
    }

    public String getPositionOfQuestion() {
        return positionOfQuestion;
    }

    public String getAnswerOfStudent() {
        return answerOfStudent;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPositionOfQuestion(String positionOfQuestion) {
        this.positionOfQuestion = positionOfQuestion;
    }

    public void setAnswerOfStudent(String answerOfStudent) {
        this.answerOfStudent = answerOfStudent;
    }

    @Override
    public String toString() {
        return category + "*" + positionOfQuestion + "*" + answerOfStudent+"\n";
    }
    
    public String rowForReportHtml(){
        return "<tr><td>"+category+"</td><td>"+question+"</td><td>"+answerOfStudent+"</td></tr>";
    }
    
}
