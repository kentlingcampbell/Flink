//package edu.cu.boulder.cs.flink.triangles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadNetFlow5Csv_parse_v2
{
  public static void main(String[] args)
  {
    //Input file which needs to be parsed
    String fileToParse = "./HelloWorld/testfile2.csv";
    BufferedReader csvReader = null;

    //Delimiter used in CSV file
    final String DELIMITER = ",";                     // use comma as separator

    try
    {
      String line = "";
      //parsing a CSV file into BufferedReader class constructor
      csvReader = new BufferedReader(new FileReader(fileToParse));

      //Read the file line by readLine
      while ((line = csvReader.readLine()) != null)
      {
        //Get all tokens available in line
        String[] data = line.split(DELIMITER);
        //System.out.println(data[0] + ' ' + data[10] + ' ' + data[11]);
        //x = data[0];
        //parseData(data[6]);
        //new Netflow();
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
    private static void parseData(String str){
    String amount;
    //Scanner lineScanner = new Scanner(str);
    //lineScanner.useDelimiter(",");
    //while(lineScanner.hasNext()){
    //  acctFrom = lineScanner.next();
    //  acctTo = lineScanner.next();
    //  amount = lineScanner.next();
    System.out.println("Value");
    }
    //lineScanner.close();
  }
