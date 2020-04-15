package com.dooditrol.snake.game;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TableOfRecords {
    
    private final int MAX_NUMBER_OF_RECORDS = 5;

    private File fileOfTable;    
    private ArrayList<Record> records;
    private int minScore;

    {
        records = new ArrayList<Record>();
        minScore = 0;
    }
    
    public TableOfRecords(String fileName) {
        
        this.fileOfTable = new File(fileName);
        
        if (!fileOfTable.exists()) {
            try {
                fileOfTable.createNewFile();
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        try (FileReader fr = new FileReader(fileOfTable);
                BufferedReader reader = new BufferedReader(fr)) {
            
            String line = reader.readLine();
            
            for (int i = 0; i < MAX_NUMBER_OF_RECORDS; i++) {
                
                if (line != null) {
                    String[] itemsOfRecord = line.split("\\s");
                    int score = Integer.parseInt(itemsOfRecord[1], 10);
                    records.add(new Record(itemsOfRecord[0], score));
                    minScore = score;
                    line = reader.readLine();
                }
                else {
                    records.add(new Record("---", 0));
                }
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ArrayList<Record> getRecords() {
        
        return records;
    }
    
    protected boolean isNewRecord(int score) {
        
        if ((score != 0) 
                && ((score > minScore) 
                        || (score == minScore && records.get(records.size() - 1).getScore() == 0))) {
            return true;
        }
        else {
            return false;
        }
    }
    
    protected void addNewRecord(String nameOfRecord, int score) {
        
        for (int i = 0; i < records.size(); ++i) {
            
            if (records.get(i).getScore() < score) {
                records.add(i, new Record(nameOfRecord, score));
                records.remove(records.size() - 1);
                break;
            }
        }
        minScore = records.get(records.size() - 1).getScore();
        
        try (FileWriter writer = new FileWriter(fileOfTable, false)){
            
            for (Record record : records) {
                
                writer.write(record.getName());
                writer.write(" ");
                writer.write(Integer.toString(record.getScore()));
                writer.write("\n");                
            }
            
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }   
}
