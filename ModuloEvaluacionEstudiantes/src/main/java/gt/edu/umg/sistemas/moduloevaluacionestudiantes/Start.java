/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.sistemas.moduloevaluacionestudiantes;

import gt.edu.umg.sistemas.moduloevaluacionestudiantes.Management.ExamManagement;
import gt.edu.umg.sistemas.moduloevaluacionestudiantes.Management.ReportThread;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 *
 * @author shampussy
 */
public class Start {

    /**
     * esta funcion se encarga de llenar un hashmap en donde tendra la categorias
     * con sus respectivos # tambien creara el semafoto colocandolo en 1(disponible)
     * asi mismo creara y ejecutara el hilo encargado de la generacion del reporte
     * y tambien mostrata el menu principal en donde el estudiante podra seleccionar
     * sobre que categoria quiere su evaluacion 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        HashMap<Integer, String> categoriesAvailable = new HashMap<>();
        categoriesAvailable.put(1, "BASIC_COMMANDS");
        categoriesAvailable.put(2, "SHELL_SCRIPTS");
        categoriesAvailable.put(3, "SECURE_SHELL");
        categoriesAvailable.put(4, "POSIX_SEMAPHORES");
        categoriesAvailable.put(5, "MAVEN");
        categoriesAvailable.put(6, "JAVA_THREADS");
        categoriesAvailable.put(7, "DOCKERS");
        int option = 0;
        Semaphore semaphoreWrite = new Semaphore(1);
        ReportThread report = new ReportThread(semaphoreWrite);
        report.start();
        do {
            ExamManagement test = new ExamManagement(semaphoreWrite);
            System.out.println("Menu Principal  ");
            System.out.println("Seleccione su categoria:");
            System.out.println("===========================");
            for (Map.Entry m : categoriesAvailable.entrySet()) {
                System.out.println(m.getKey() + " " + m.getValue());
            }
            System.out.println("8 Salir    ");
            option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1: {
                    test.testStudent(categoriesAvailable.get(1));
                    break;
                }
                case 2: {
                    test.testStudent(categoriesAvailable.get(2));
                    break;
                }
                case 3: {
                    test.testStudent(categoriesAvailable.get(3));
                    break;
                }
                case 4: {
                    test.testStudent(categoriesAvailable.get(4));
                    break;
                }
                case 5: {
                    test.testStudent(categoriesAvailable.get(5));
                    break;
                }
                case 6: {
                    test.testStudent(categoriesAvailable.get(6));
                    break;
                }
                case 7: {
                    test.testStudent(categoriesAvailable.get(7));
                    break;
                }
                case 8: {
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Opcion Disponible Proximamente...\nElija otra Opcion");
                    break;
                }
            }
        } while (option != 8);
        report.join();
    }
}
