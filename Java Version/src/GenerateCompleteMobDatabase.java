import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateCompleteMobDatabase {
    public static void main(String[] args) throws IOException {
        File db_mobs = new File("Result/MobStats.csv");
        File db_strings = new File("Result/Strings.csv");
        File db_out = new File("Result/CleanMobDB.csv");
        CSVReader reader_mobs = new CSVReader(new FileReader(db_mobs));
        CSVReader reader_strings = new CSVReader(new FileReader(db_strings));
        ArrayList<String[]> mob_strings = (ArrayList<String[]>) reader_strings.readAll();
        reader_strings.close();
        CSVWriter writer = new CSVWriter(new FileWriter(db_out));

        String[] record;
        while( (record = reader_mobs.readNext()) != null ) {
            String monster_id = record[0];
            String monster_name = "";
            //Search for id in strings to get monster name.
            for(String[] record2 : mob_strings) {
                if(record2[0].equals(monster_id)) {
                    monster_name = record2[1];
                    break;
                }
            }

            String[] nextLine = { monster_id,monster_name,record[1],record[2],record[3],record[4],record[5] };
            writer.writeNext(nextLine);
        }
        reader_mobs.close();
        writer.close();
    }
}
