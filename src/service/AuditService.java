package service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static String path = "src/utils/CSVfiles/audit.csv";
    private static AuditService instance = null;
    private AuditService(){

    }
    public static AuditService getInstance(){
        if(instance == null){
            instance = new AuditService();
        }
        return instance;
    }
    public void write(String action){
        try(BufferedWriter fout = new BufferedWriter(new FileWriter(path, true))){
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = time.format(formatter);
            fout.write(action);
            fout.write(formattedDateTime);
            fout.newLine();
        }
        catch (FileNotFoundException exception) {
            System.out.println("This file doesn't exist!\n");
        }
        catch(IOException exception) {
            System.out.println("Error at writing in the file!\n");
        }
    }
}
