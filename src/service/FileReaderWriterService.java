package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface FileReaderWriterService<T>{
    T objectFromCSVLine(String line);
    String CSVLineFromObject(T object);
    default List<T> read(String path) throws IOException {
        List<T> objects = new ArrayList<>();
        try(BufferedReader fin = new BufferedReader(new FileReader(path))){
            String line;
            while((line = fin.readLine()) != null){
                T object = objectFromCSVLine(line);
                if(object != null)
                    objects.add(object);
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("This file doesn't exist!\n");
        }
        catch(IOException exception) {
            System.out.println("Error at reading the file!\n");
        }
        return objects;
    }
    default void write(String path, T object){
        try(BufferedWriter fout = new BufferedWriter(new FileWriter(path, true))){
            fout.write(CSVLineFromObject(object));
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
