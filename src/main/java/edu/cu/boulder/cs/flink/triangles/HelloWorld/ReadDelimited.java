import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadDelimited {
  public static void main(String[] args) {
    Scanner sc = null;
    try {
      sc = new Scanner(new File("D:\\acct.csv"));

      // Check if there is another line of input
      while(sc.hasNextLine()){
        String str = sc.nextLine();
        // parse each line using delimiter
        parseData(str);
      }
    } catch (IOException  exp) {
      // TODO Auto-generated catch block
      exp.printStackTrace();
    }finally{
      if(sc != null)
        sc.close();
    }       
  }
  
  private static void parseData(String str){  
    String acctFrom, acctTo, amount;
    Scanner lineScanner = new Scanner(str);
    lineScanner.useDelimiter(",");
    while(lineScanner.hasNext()){
      acctFrom = lineScanner.next();
      acctTo = lineScanner.next();
      amount = lineScanner.next();
      System.out.println("Account From- " + acctFrom + " Account To- " + acctTo + 
       " Amount- " + amount);  
    }
    lineScanner.close();
  }
}