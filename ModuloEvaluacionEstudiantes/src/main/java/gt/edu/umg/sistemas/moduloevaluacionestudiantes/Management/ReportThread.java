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
    private final String localizationQuestions = "/usr/src/database/preguntas.log";
    private final String localizationAnswers = "/usr/src/database/respuestas.log";
    private final String localizationReport = "/usr/src/reports/Answers/Proyecto/Respuestas.html";
    private final String delimiter = "*";

    public ReportThread(Semaphore semaphoreWrite) {
        this.semaphoreWrite = semaphoreWrite;
    }

    /**
     * Esta funcion se encarga de verificar el semaforo si esta disponible,
     * si lo esta escribe el reporte con los datos que leera del archivo de respuestas
     * tomara la categoria, y la respuesta del estudiante y la almacenara en el objeto
     * llamado answer, tomara el numero de linea en donde se encuentra la pregunta, 
     * el cual utilizara para leer en el archivo de preguntas la correcta, tomara
     * la pregunta y la almacenara en el objeto answer para posteriormente utilizar el 
     * metodo rowForReportHtml de la clase Answers que retorna la fila con el formato
     * html adecuado, el cual se escribira en el archivo Respuestas.html.
     * Al terminar el archivo respuestas cerrara todos los archivos y liberara
     * el semaforo para pasar a esperar 10s antes de repetir todo lo anterior.
     */
    @Override
    public void run() {
        while (true) {
            try {
                semaphoreWrite.acquire();

                String lineAnswer = "", question = "";
                FileReader fileAnswers = new FileReader(localizationAnswers);
                FileWriter fileWrite = new FileWriter(localizationReport);
                fileWrite.write("<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "<head>\n"
                        + "  <title>Report Answers</title>\n"
                        + "  <meta charset=\"utf-8\">\n"
                        + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                        + "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n"
                        + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n"
                        + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n"
                        + "</head>\n"
                        + "<body>\n"
                        + "\n"
                        + "<div class=\"container\">\n"
                        + " \n"
                        + "  <table class=\"table\">\n"
                        + "    <thead>\n"
                        + "      <tr style=\"background-color:#3aaeea; color:#FFF; font-size:24px;\">\n"
                        + "        <th>Categoria</th>\n"
                        + "        <th>Pregunta</th>\n"
                        + "        <th>Respuesta</th>\n"
                        + "      </tr>\n"
                        + "    </thead>\n"
                        + "    <tbody>");
                BufferedReader buffOfFile = new BufferedReader(fileAnswers);
                while ((lineAnswer = buffOfFile.readLine()) != null) {
                    Answers answer = new Answers();
                    StringTokenizer stringToken = new StringTokenizer(lineAnswer, delimiter);
                    answer.setCategory(stringToken.nextToken());
                    int lineQuestion = Integer.parseInt(stringToken.nextToken());
                    answer.setAnswerOfStudent(stringToken.nextToken());
                    int numberOfLine = 0;
                    FileReader fileQuestion = new FileReader(localizationQuestions);
                    BufferedReader buffOfFileQuestion = new BufferedReader(fileQuestion);
                    while ((question = buffOfFileQuestion.readLine()) != null) {
                        numberOfLine = numberOfLine + 1;
                        if (lineQuestion == numberOfLine) {
                            StringTokenizer stringTokenQuestion = new StringTokenizer(question, delimiter);
                            stringTokenQuestion.nextToken();
                            answer.setQuestion(stringTokenQuestion.nextToken());
                        }
                    }
                    buffOfFileQuestion.close();
                    fileQuestion.close();
                    fileWrite.write(answer.rowForReportHtml() + "\n");

                }
                buffOfFile.close();
                fileAnswers.close();
                fileWrite.write("</tbody>\n"
                        + "</table>\n"
                        + "</div>\n"
                        + "</body>\n"
                        + "</html>");
                fileWrite.close();
                semaphoreWrite.release();
                sleep(10);
            } catch (Exception execp) {
                System.out.println("Error :" + execp.getMessage());
            }
        }
    }

}
