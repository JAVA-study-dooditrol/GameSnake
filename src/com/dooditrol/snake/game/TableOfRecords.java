package com.dooditrol.snake.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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
        
        if (fileOfTable.exists()) {
            
            try (FileInputStream fis = new FileInputStream(fileOfTable);
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                                
                int numberRecords = ois.readInt();
                
                for (int i = 0; i < numberRecords; ++i) {
                    
                    records.add((Record) ois.readObject());
                }
                
                if (numberRecords == MAX_NUMBER_OF_RECORDS) {
                    minScore = records.get(records.size() - 1).getScore();
                }
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else {
            
            try {
                fileOfTable.createNewFile();
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
            try (FileOutputStream fos = new FileOutputStream(fileOfTable);
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                
                oos.writeInt(0);
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    protected int getMaxSizeTable() {
        
        return MAX_NUMBER_OF_RECORDS;
    }
    
    protected ArrayList<Record> getRecords() {
        
        return records;
    }
    
    protected boolean isNewRecord(int score) {
        
        if ((score != 0) 
                && ((score > minScore) 
                        || (score == minScore && records.size() < MAX_NUMBER_OF_RECORDS))) {
            return true;
        }
        else {
            return false;
        }
    }
    
    protected void addNewRecord(String nameOfRecord, int score) {
        
        if (records.size() > 0) {
            int i = 0;
            
            for (; i < records.size(); ++i) {
                
                if (records.get(i).getScore() < score) {
                    break;
                }
            }
            records.add(i, new Record(nameOfRecord, score));
        }
        else {
            records.add(new Record(nameOfRecord, score));
        }
        
        if (records.size() > MAX_NUMBER_OF_RECORDS) {
            records.remove(records.size() - 1);
            minScore = records.get(records.size() - 1).getScore();
        }
        
        try (FileOutputStream fos = new FileOutputStream(fileOfTable);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            
            oos.writeInt(records.size());
            
            for (Record record : records) {
                
                if (record.getScore() == 0) {
                    break;
                }
                else {
                    oos.writeObject(record);
                }
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }   
}
