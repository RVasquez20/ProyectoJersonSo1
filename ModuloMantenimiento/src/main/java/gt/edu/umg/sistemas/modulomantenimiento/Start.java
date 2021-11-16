package gt.edu.umg.sistemas.modulomantenimiento;

import gt.edu.umg.sistemas.modulomantenimiento.ManagementQuestions.ManagementQuestions;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shampussy
 */
public class Start {
    public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
       final String localization="/home/ghostman/volume6/Preguntas.csv";
      ManagementQuestions management=new ManagementQuestions();
      File file=new File(localization);
        if(!file.exists()){
          try {
              file.createNewFile();
          } catch (IOException ex) {
              Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        while(true){
        int opcion = 0;
        System.out.println("Menu Principal  ");
        System.out.println("==================");
        System.out.println("1)Listado de Preguntas Disponibles ");
        System.out.println("2)Agregar Nueva Pregunta");
        System.out.println("3)Salir    ");
        opcion = scan.nextInt();
        scan.nextLine();
        switch (opcion) {
            case 1: {
               management.listOfQuestionsAvailable();
                break;
            }
            case 2: {
                management.addNewQuestion();
                break;
            }
            case 3: {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Opcion Disponible Proximamente...\nElija otra Opcion");
                break;
            }
        }
    }  
    }
    
}
