package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFileManager {
    CSVParser csvParser;
    Iterable<CSVRecord> csvRecord;

    public CSVFileManager(String csvFilePath) {
        try {
            FileReader fileReader = new FileReader(csvFilePath);
            csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(fileReader);
            FileReader reader = new FileReader(csvFilePath);
            csvRecord = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getColumnName() {
        try {
            return new ArrayList<>(csvParser.getHeaderNames());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String[]> getRows() {
        List<String[]> rows = new ArrayList<>();
        try {
            for (CSVRecord record : csvRecord) {
                String[] data = new String[record.size()];
                for (int i = 0; i < data.length; i++) {
                    data[i] = record.get(i);
                }
                rows.add(data);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return rows;
    }

}