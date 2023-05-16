package utils;

import model.Drink;
import service.FileReaderWriterService;

public class DrinkReaderWriter implements FileReaderWriterService<Drink> {
    private static DrinkReaderWriter instance = null;
    private DrinkReaderWriter(){

    }
    public static DrinkReaderWriter getInstance(){
        if(instance == null){
            instance = new DrinkReaderWriter();
        }
        return instance;
    }
    @Override
    public Drink objectFromCSVLine(String line) {
        String[] drinkData = line.split(",");
        return new Drink(drinkData[0], Boolean.parseBoolean(drinkData[1]), Double.parseDouble(drinkData[2]),
                Double.parseDouble(drinkData[3]), drinkData[4], Boolean.parseBoolean(drinkData[5]));
    }

    @Override
    public String CSVLineFromObject(Drink object) {
        return object.getId() + ',' + object.getName() + ',' + object.getWeight() + ',' + object.getFlavour() + ',' + object.getPrice();
    }
}
