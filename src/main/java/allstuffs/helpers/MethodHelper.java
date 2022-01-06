package allstuffs.helpers;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import jxl.Sheet;
import jxl.Workbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class MethodHelper {
    public String formatCurrentDate() {
        String pattern = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
    public static Object[][] ProvideData(String xlFilePath, String sheetName) throws Exception{
        //open excel file
        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        //the required sheet
        Sheet sheet = workbook.getSheet(sheetName);
        //return number of rows(rowCount)
        int rowCount = sheet.getRows()-1;
        int columnCount = sheet.getColumns();
        int currentPosition = 1;
        Object[][] values = new Object[rowCount][columnCount];
        for(int i = 0 ; i < rowCount ; i++, currentPosition++){
            //loop over the rows
            for(int j = 0 ; j < columnCount ; j++)
                values[i][j] = sheet.getCell(j, currentPosition).getContents();
        }
        workbook.close();
        return values;
    }
    public HashMap<String, String>readCSV(String cityNameFile) {
        String fileName = "src/test/resources/filedatatest/"+ cityNameFile + ".csv" ;
        HashMap<String, String> listCountryName = new HashMap<>();
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileName))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1)           // skip the first line, header info
                .build()){
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                String[] country = lineInArray[0].split(":");
                listCountryName.put(country[0], country[1]);
            }
        } catch (FileNotFoundException | CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCountryName;
    }
    public String[] readLanguageCSV(String cityNameFile, String getLine) {
        String fileName = "src/test/resources/filedatatest/"+ cityNameFile + ".csv" ;
        String[] listElement = null;
        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build(); // custom separator
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileName))
                .withCSVParser(csvParser)   // custom CSV parser
                .build()){
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                String[] country = lineInArray[0].split(":");
                if(country[0].equals(getLine)){
                    listElement = country;
                }
            }
        } catch (FileNotFoundException | CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listElement;
    }
    public Object[][]hashMapToDataProvider(HashMap sourceHashMap) {
        Object[][] result = new Object[sourceHashMap.size()][2];
        Object[] keys = sourceHashMap.keySet().toArray();
        Object[] values = sourceHashMap.values().toArray();
        for (int i = 0; i < keys.length; i++) {
            result[i][0] = keys[i];
            result[i][1] = values[i];
        }
        return result;
    }
    public boolean isStringInteger(String number ){
        try{
            Integer.parseInt(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }

    }