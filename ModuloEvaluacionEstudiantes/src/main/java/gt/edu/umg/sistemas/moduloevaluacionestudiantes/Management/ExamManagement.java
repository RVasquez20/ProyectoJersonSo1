/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.sistemas.moduloevaluacionestudiantes.Management;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

/**
 *
 * @author shampussy
 */
public class ExamManagement {

    public Semaphore semaphoreWrite;
    private Scanner scan = new Scanner(System.in);
    private LinkedList<Integer> positionsLine = new LinkedList<>();
    private LinkedList<Integer> positionsSelectedRandom = new LinkedList<>();
    private final String localizationQuestions = "/usr/src/database/preguntas.log";
    private final String localizationAnswers = "/usr/src/database/respuestas.log";
    private final String delimiter = "*";

    public ExamManagement(Semaphore semaphoreWrite) {
        this.semaphoreWrite = semaphoreWrite;
    }
/**
 * esta funcion llama a la funcion searchAndSelectQuestions para despues iterar
 * sobre cada uno de los elementos que contiene, luego con cada numero de linea
 * ira a buscar al archivo de preguntas esa misma linea y tomara la pregunta,
 * luego mostrara en pantalla la pregunta , esperara que el usuario la responda
 * y luego almacera en un objeto 
 * llamado answers la categoria el numero de linea donde esta la pregunta y 
 * la respuesta del estudiante luego verificara el estado del semaforo y si 
 * esta disponible lo bloqueara para escribir los datos almacenados en answers
 * en el archivo de respuestas, luego libera el semaforo
 * @param categorySelectedOfStudent 
 */
    public void testStudent(String categorySelectedOfStudent) {
        searchAndSelectQuestions(categorySelectedOfStudent);
        Answers answers = new Answers();
        System.out.println("Examen de La Categoria " + categorySelectedOfStudent);
        System.out.println("=====================================================");
        for (Integer linePosition : positionsSelectedRandom) {
            int numberOfLine = 0;
            try {
                String readLine = "";
                FileReader file = new FileReader(localizationQuestions);
                BufferedReader buffOfFile = new BufferedReader(file);
                while ((readLine = buffOfFile.readLine()) != null) {
                    numberOfLine++;
                    StringTokenizer stringToken = new StringTokenizer(readLine, delimiter);
                    if (numberOfLine == linePosition) {
                        answers.setCategory(stringToken.nextToken());
                        answers.setPositionOfQuestion(String.valueOf(numberOfLine));
                        String question = stringToken.nextToken();
                        System.out.println("Pregunta:" + question);
                        System.out.println("Respuesta:");
                        answers.setAnswerOfStudent(scan.nextLine());
                        System.out.println("=====================================================");
                        semaphoreWrite.acquire();
                        try {
                            FileWriter fileWrite = new FileWriter(localizationAnswers, true);
                            fileWrite.append(answers.toString());
                            fileWrite.close();
                        } catch (Exception execp) {
                            System.out.println("Error :" + execp.getMessage());
                        }

                        semaphoreWrite.release();
                    }
                }
                buffOfFile.close();
                file.close();
            } catch (Exception execp) {
                System.out.println("Error :" + execp.getMessage());
            }

        }
    }
/**
 * esta funcion recibe como parametro la categoria que el estudiante escoge
 * la cual sirve para buscar en el archivo de preguntas todos los numeros 
 * de linea que contengan esa categoria y los almacenara en una linkedlist
 * posteriormente sleccionara 3 numeros aleatorios de la anterior linkedlist 
 * mencionada y los agregara a una linkedlist llamada positionsSelectedRandom
 * @param categorySelectedOfStudent 
 */
    public void searchAndSelectQuestions(String categorySelectedOfStudent) {
        int numberOfLine = 0;
        //Process of search number of line for question with this category
        try {
            String readLine = "";
            FileReader file = new FileReader(localizationQuestions);
            BufferedReader buffOfFile = new BufferedReader(file);
            while ((readLine = buffOfFile.readLine()) != null) {
                numberOfLine++;
                StringTokenizer stringToken = new StringTokenizer(readLine, delimiter);
                if (stringToken.nextToken().equals(categorySelectedOfStudent)) {
                    positionsLine.add(numberOfLine);
                    stringToken.nextToken();
                }
            }
            buffOfFile.close();
            file.close();
        } catch (Exception execp) {
            System.out.println("Error :" + execp.getMessage());
        }

        //process of fill linkedlist with random values of linkedlist with name positionsLine
        do {
            Random randomPositionInList = new Random();
            int positionRandom = randomPositionInList.nextInt(positionsLine.size());
            if (positionsSelectedRandom.contains(positionsLine.get(positionRandom)) == false) {
                positionsSelectedRandom.add(positionsLine.get(positionRandom));
            }
        } while (positionsSelectedRandom.size() < 3);
    }

}
