import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.math.BigInteger;

public class EXP_div_HP_GenerateDB {
    public static void main(String[] args) throws IOException {
        CSVReader reader_db = new CSVReader(new FileReader("Result/CleanMobDB.csv"));
        CSVWriter writer = new CSVWriter(new FileWriter("Result/CompleteDatabase.csv"));

        String[] record;
        String[] header = reader_db.readNext(); //skip header


        String[] new_header = new String[header.length+1];
        System.out.println("Header Len = " + header.length);
        System.out.println("New Header Len = " + new_header.length);
        for(int i = 0; i < header.length; i++)
            new_header[i] = header[i];
        new_header[header.length] = "exp_div_hp";
        writer.writeNext(new_header); //write new header


        while( (record = reader_db.readNext()) != null) {
            String monster_exp_str = record[3];
            String monster_hp_str = record[5];

            String[] new_line = new String[record.length+1];
            for(int i = 0; i < record.length; i++)
                new_line[i] = record[i];


            if(monster_exp_str.length() == 0 || monster_hp_str.length() == 0) {
                new_line[record.length] = "";
            } else {
                BigInteger monster_exp = new BigInteger(monster_exp_str);
                BigInteger monster_hp = new BigInteger(monster_hp_str);

                double div = monster_exp.doubleValue() / monster_hp.doubleValue();
                new_line[record.length] = ""+div;
            }
            writer.writeNext(new_line);
        }

        reader_db.close();
        writer.close();
    }
 }
