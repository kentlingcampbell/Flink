import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;

public class ReadNetFlow5Csv
{
  public static void main(String[] args)
  {
    //Input file which needs to be parsed
    String fileToParse = "testfile.csv";
    BufferedReader csvReader = null;

    //Delimiter used in CSV file
    final String DELIMITER = ",";                     // use comma as separator

    try
    {
      String line = "";
      //parsing a CSV file into BufferedReader class constructor
      csvReader = new BufferedReader(new FileReader(fileToParse));

      //Read the file line by readLine
      while ((line = csvReader.readLine()) != null)   //returns a Boolean value
      {
        //Get all tokens available in line
        String[] data = line.split(DELIMITER);
          for(String token : data)
          {
            System.out.println(token);
          }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally
    {
      try {
        csvReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
