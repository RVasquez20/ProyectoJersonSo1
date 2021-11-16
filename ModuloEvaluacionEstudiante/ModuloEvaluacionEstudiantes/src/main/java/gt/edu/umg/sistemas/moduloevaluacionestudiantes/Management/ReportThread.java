/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.sistemas.moduloevaluacionestudiantes.Management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

/**
 *
 * @author shampussy
 */
public class ReportThread extends Thread {

    public Semaphore semaphoreWrite;
    private final String localizationQuestions = "/home/ghostman/volume6/Preguntas.csv";
    private final String localizationAnswers = "/home/ghostman/volume6/Respuestas.log";
    private final String localizationReport = "/home/ghostman/volume6/Respuestas.html";
    private final String delimiter = "*";

    public ReportThread(Semaphore semaphoreWrite) {
        this.semaphoreWrite = semaphoreWrite;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaphoreWrite.acquire();
                
                String lineAnswer = "", question = "";
                FileReader fileAnswers = new FileReader(localizationAnswers);
                FileWriter fileWrite = new FileWriter(localizationReport);
                fileWrite.write("<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "<style>\n"
                        + "table, th, td {\n"
                        + "  border: 1px solid black;\n"
                        + "}\n"
                        + "</style>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "<table>\n"
                        + "  <thead>\n"
                        + "    <tr>\n"
                        + "      <th>CATEGORIA</th>\n"
                        + "      <th>PREGUNTA</th>\n"
                        + "      <th>RESPUESTA</th>\n"
                        + "    </tr>\n"
                        + "  </thead>\n"
                        + "  <tbody>\n");
                BufferedReader buffOfFile = new BufferedReader(fileAnswers);
                while ((lineAnswer = buffOfFile.readLine()) != null) {
                    Answers answer = new Answers();
                     StringTokenizer stringToken = new StringTokenizer(lineAnswer, delimiter);
                    answer.setCategory(stringToken.nextToken());
                    int lineQuestion=Integer.parseInt(stringToken.nextToken());
                    answer.setAnswerOfStudent(stringToken.nextToken());
                    int numberOfLine = 0;
                     FileReader fileQuestion = new FileReader(localizationQuestions);
                BufferedReader buffOfFileQuestion = new BufferedReader(fileQuestion);
                while ((question = buffOfFileQuestion.readLine()) != null) {
                    numberOfLine=numberOfLine+1;
                    if(lineQuestion==numberOfLine){
                        StringTokenizer stringTokenQuestion = new StringTokenizer(question, delimiter);
                        stringTokenQuestion.nextToken();
                        answer.setQuestion(stringTokenQuestion.nextToken());
                    } 
                }
                buffOfFileQuestion.close();
                fileQuestion.close();
                 fileWrite.write(answer.rowForReportHtml()+"\n");   
                    
                }
                buffOfFile.close();
                fileAnswers.close();
                fileWrite.write("</tbody>\n" +
"</table>\n" +
"</body>\n" +
"</html>");
                fileWrite.close();
                semaphoreWrite.release();
                sleep(10);
            } catch (Exception execp) {
                System.out.println("Error :" + execp.getMessage());
            }
        }
    }

}
