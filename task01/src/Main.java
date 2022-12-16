import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
  // Read CSV File
  // Each Line -> Hashmap(Title, Value)
  // List of Lines

  public static void main(String[] args) throws IOException {

    if(args.length<2) System.out.println("Error! Usage: <program> <csv_file> <txt_file>");

    String csvString = args[0];
    String templateFileString = args[1];

    //List of Mapped data
    List<Map<String,String>> csvData = new LinkedList<Map<String,String>>();

    try {
      // read csv file first line
      BufferedReader bfr = new BufferedReader(new FileReader(csvString));
      String[] headerList = bfr.readLine().split(",");
      String line;
      while((line=bfr.readLine())!=null){
        //initialise HashMap
        Map<String, String> data = new HashMap<String, String>();
        String[] dataLine = line.split(",");
        for(Integer i=0;i<headerList.length;i++){
          data.put(headerList[i],dataLine[i]);
        }
        csvData.add(data);
      }
      
      //Store Edited Template String
      List<List<String>> allLetters = new LinkedList<List<String>>();

      for(Integer i=0;i<csvData.size();i++){
        Map<String,String> csvLine = csvData.get(i);
        //read template file
        BufferedReader br = new BufferedReader(new FileReader(templateFileString));
        String templateLine;
        List<String> letter = new LinkedList<String>();
        while((templateLine = br.readLine())!=null){
        String[] templateLineText = templateLine.split(" ");

        for(Integer j=0;j<templateLineText.length;j++){
          //If header matches, find corresponding value
          if(templateLineText[j].contains("<<") && templateLineText[j].contains(">>")){
            String headerMatch = templateLineText[j].substring(templateLineText[j].indexOf("<<")+2);
            headerMatch = headerMatch.substring(0,headerMatch.indexOf(">>"));
            
            String matchValue = csvLine.get(headerMatch);
            templateLineText[j] = templateLineText[j].replace(String.format("<<%s>>", headerMatch),matchValue);
          }
        }
       

        String letterLine = String.join(" ", templateLineText);
        
        letterLine = letterLine.replace("\\n", "\n");
        letter.add(letterLine);
        
      }
      String fullLetter = String.join("\n",letter);
      System.out.println(fullLetter);
      System.out.println("===============");
      }
      
      
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

}
