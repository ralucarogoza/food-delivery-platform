package utils;

import model.Dish;
import service.FileReaderWriterService;

public class DishReaderWriter implements FileReaderWriterService<Dish> {
    private static DishReaderWriter instance = null;
    private DishReaderWriter(){

    }
    public static DishReaderWriter getInstance(){
        if(instance == null){
            instance = new DishReaderWriter();
        }
        return instance;
    }
    @Override
    public Dish objectFromCSVLine(String line) {
        String[] dishData = line.split(",");
        return new Dish(dishData[0], Boolean.parseBoolean(dishData[1]), Double.parseDouble(dishData[2]), Double.parseDouble(dishData[3]), Integer.parseInt(dishData[4]));
    }

    @Override
    public String CSVLineFromObject(Dish object) {
        return object.getId() + ',' + object.getName() + ',' + object.getWeight() + ',' + object.getCalories() + ',' + object.getPrice();
    }
}