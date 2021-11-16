/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.sistemas.modulomantenimiento.ManagementQuestions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shampussy
 */
public class ManagementQuestions {
    private Scanner scan = new Scanner(System.in);
    private HashMap<Integer,String>categoriesAvailable=new HashMap<>();
    private final String localization="/home/ghostman/volume6/Preguntas.csv";
    private final String delimiter="*";
    public ManagementQuestions() {
    }
    
    
    public void listOfQuestionsAvailable(){
        long numberOfLine=0;
        System.out.println("Listado de Preguntas");
        System.out.println("=====================");
        System.out.println("Numero   \t\tCategoria\t\tPregunta");
        System.out.println("=============================================================");
        try {
            String readLine="";
            FileReader file = new FileReader(localization);
            BufferedReader buffOfFile = new BufferedReader(file);
            while ((readLine = buffOfFile.readLine()) != null) {
                numberOfLine++;
                
                StringTokenizer stringToken = new StringTokenizer(readLine, delimiter);
                System.out.println(numberOfLine+"\t\t"+ stringToken.nextToken()+"\t\t"+ stringToken.nextToken());
            }
            buffOfFile.close();
            file.close();
        } catch (Exception execp) {
            System.out.println("Error :" + execp.getMessage());
        }
        
    }
    
    public String listOfCategoriesAvailable(){
        fillCategories();
        String selectedCategory="";
        System.out.println("Seleccione la Categoria que desea:");
        for(Map.Entry m : categoriesAvailable.entrySet()){    
    System.out.println(m.getKey()+" "+m.getValue());    
   }  
        int selected=scan.nextInt();
        if(categoriesAvailable.containsKey(selected)){
             selectedCategory=categoriesAvailable.get(selected);    
        }else{
            System.out.println("Categoria Seleccionada no Disponible");
        }
        return selectedCategory;
    }

    private void fillCategories() {
        categoriesAvailable.put(1, "BASIC_COMMANDS");
        categoriesAvailable.put(2, "SHELL_SCRIPTS");
        categoriesAvailable.put(3, "SECURE_SHELL");
        categoriesAvailable.put(4, "POSIX_SEMAPHORES");
        categoriesAvailable.put(5, "MAVEN");
        categoriesAvailable.put(6, "JAVA_THREADS");
        categoriesAvailable.put(7, "DOCKERS");        
    }
    
    public void addNewQuestion(){
        FileWriter file=null;
        try {
            System.out.println("Agregar Pregunta nueva");
            System.out.println("=======================================");
            String selectedUserCategory=listOfCategoriesAvailable();scan.nextLine();
            System.out.println("Ingrese La nueva Pregunta:");
            String newQuestion=scan.nextLine();
            file = new FileWriter(localization, true);
            file.append(selectedUserCategory+"*"+ newQuestion + "\n");
            file.flush();
            file.close();
            System.out.println("Pregunta Agregada Exitosamente :D"); 
        } catch (IOException ex) {
            Logger.getLogger(ManagementQuestions.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
}
