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
    private String[] colores={"F0FFFF","F0FFF0","FFF0F5","AFEEEE","DCDCDC","87CEFA","FFDAB9"};
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
        int pos=colorSelectedOfCategorie();
        return "<tr style=\"background-color:#"+colores[pos]+";\"><td>"+category+"</td><td>"+question+"</td><td>"+answerOfStudent+"</td></tr>";
    }

    private int colorSelectedOfCategorie() {
        int pos=0;
        switch(category){
            case "BASIC_COMMANDS":{
                pos=0;
                break;
            }
            case "SHELL_SCRIPTS":{
                pos=1;
                break;
            }
            case "SECURE_SHELL":{
                pos=2;
                break;
            }
            case "POSIX_SEMAPHORES":{
                pos=3;
                break;
            }
            case "MAVEN":{
                pos=4;
                break;
            }
            case "JAVA_THREADS":{
                pos=5;
                break;
            }
            case "DOCKERS":{
                pos=6;
                break;
            }
            
        }
        return pos;
    }
    
    
}
