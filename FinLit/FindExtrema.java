import java.util.*;
import java.io.*;
import java.net.*; 

// I needed to get 52 week high and lows for a stock, and did not want to look through tables and could not find the right graph online, so I made this using yahoo's data from:
// http://finance.yahoo.com/q/hp?s=GOOGL&a=00&b=19&c=2015&d=04&e=21&f=2016&g=d
// http://real-chart.finance.yahoo.com/table.csv?s=GOOGL&a=03&b=22&c=2015&d=03&e=22&f=2016&g=d&ignore=.csv

// BIG IDEA: MACHINE LEARNING.
// Take previous stock price data and take data of major events, and match up the dates to see tendencies.

// Finds 52 week high and low

public class FindExtrema { 

   public static void main(String[] args) { 

      String tableLoc = args[0];
      //String ticker = args[0];
      //String startDate = args[1];
      //String titleColumn = args[1];
     // String sortColumn = args[2]; // from zero to n-1
     // boolean firstRowHeader = Boolean.parseBoolean(args[3]);

      //String ticker = args[3];
      //String sDate = args[4];
      ////String eDate = args[5];
     // String url = getURL(ticker, sDate, eDate);

      //new FindExtrema().start(tableLoc, titleColumn, sortColumn, firstRowHeader);
      new FindExtrema().start(tableLoc);
   }

   /*public static String getURL(String ticker, String startDate, String endDate) {
      String base = "http://real-chart.finance.yahoo.com/table.csv?";
      String params = "s=0&a=1&b=2&c=3&d=4&e=5&f=6&g=d&ignore=.csv";
      String[] sDate = startDate.split("-");
      String[] eDate = endDate.split("-");
      params = params.replace("0", ticker);
      params = params.replace("1", sDate[0]);
      params = params.replace("2", sDate[1]);
      params = params.replace("3", sDate[2]);
      params = params.replace("4", eDate[0]);
      params = params.replace("5", eDate[1]);
      params = params.replace("6", eDate[2]);
      System.out.println(base + params + " : " + startDate);
      return base + params;
   } */

   public void start(String tableLoc) {
      try {
         BufferedReader br = new BufferedReader(new FileReader(tableLoc));
         br.readLine();
         String line = "";
         while (( line = br.readLine()) != null) {
            String[] data = line.split(",");
            System.out.println("Company: " + data[0] + " Date: " + data[1]);
            format(data[0], data[1]);
         }
         br.close();
         System.out.println("---------- DONE ----------");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /*public void start(String tableLoc, String titleColumn, String sortColumn, boolean firstRowHeader) {
      try {
         //URL dataURL = new URL(url); //Reading
         //URLConnection yc = dataURL.openConnection();
         // BufferedReader br = new BufferedReader(new InputStreamReader(yc.getInputStream()));
         BufferedReader br = new BufferedReader(new FileReader(tableLoc));
         if (firstRowHeader)
            br.readLine();
         String line = "";
         while (( line = br.readLine()) != null) {
            String[] data = line.split(",");
            table.add(data);
         }
         br.close();
         System.out.println("---------- DONE ----------");
         calculate(titleColumn, sortColumn);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }*/

   public void format(String ticker, String endDate) {
      List<String[]> table = new ArrayList<String[]>();

      String[] eDate = endDate.split("-");
      int mm = Integer.parseInt(eDate[0]) - 1;
      String dd = eDate[1];
      int yyE = Integer.parseInt(eDate[2]);
      int yyS = yyE - 1;

      String url = "http://real-chart.finance.yahoo.com/table.csv?s=" 
      + ticker + "&a=" + mm + "&b=" + dd + "&c=" + yyS 
      + "&d=" + mm + "&e=" + dd + "&f=" + yyE + "&g=d&ignore=.csv";

      //System.out.println(url);

      try {
         URL dataURL = new URL(url); //Reading
         URLConnection yc = dataURL.openConnection();
         BufferedReader br = new BufferedReader(new InputStreamReader(yc.getInputStream()));
         //BufferedReader br = new BufferedReader(new FileReader(tableLoc));
         br.readLine();
         String line = "";
         while (( line = br.readLine()) != null) {
            String[] data = line.split(",");
            table.add(data);
         }
         br.close();
         calculate(table, "0", "4");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void calculate(List<String[]> table, String titleColumn, String sortColumn) {
      double min = Double.MAX_VALUE;
      double max = Double.MIN_VALUE;
      int minIndex = -1;
      int maxIndex = -1;

      int title = Integer.parseInt(titleColumn);
      int sortBy = Integer.parseInt(sortColumn);

      for (int i = 0; i < table.size(); i++) {

         double val = Double.parseDouble(table.get(i)[sortBy]);
         if (val < min) {
            min = val;
            minIndex = i;
         }
         if (val > max) {
            max = val;
            maxIndex = i;
         }

      }

      System.out.format("MIN: %s : %s\n", table.get(minIndex)[title], table.get(minIndex)[sortBy]);
      System.out.format("MAX: %s : %s\n", table.get(maxIndex)[title], table.get(maxIndex)[sortBy]);

   }


}