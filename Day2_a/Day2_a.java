package Day2_a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2_a {
    public static void main(String[] args) {

        //Read in file and parse into array of ints
        String filePath = "Day2_a/input.txt";
        String[] fileContent = FileReaderToArray.readFileToArray(filePath);

        //Splits the file into cells of each number, adds to ArrayList of all nums
        fileContent = fileContent[0].split(",|-");

        ArrayList<Long> inputs = new ArrayList<>();

        for(String in : fileContent){
            inputs.add(Long.valueOf(in));
        }


        ArrayList<Long> repeatingIDs = new ArrayList<>();

        for(int i = 0; i+1 <= inputs.size(); i+=2){
            long startRange = inputs.get(i);
            long endRange = inputs.get(i+1);

            for(long j = startRange; j<= endRange; j++){
                if(IdPatterns.containsRepeating(j))
                    repeatingIDs.add(j);
            }
        }

        long passocde =0;

        for(long l : repeatingIDs)
            passocde += l;

        System.out.println(passocde);



    }


    public static class FileReaderToArray {

        public static String[] readFileToArray(String filePath) {
            ArrayList<String> lines = new ArrayList<>();
            try {
                File file = new File(filePath);
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    lines.add(scanner.nextLine());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
                return new String[0]; // Return an empty array if file not found
            }

            // Convert ArrayList to a String array
            return lines.toArray(new String[0]);
        }

    }

    public static class IdPatterns{
        
        //Checks one input number if it contains a repeating pattern, returns T/F
        public static boolean containsRepeating(long checkNum){
            final String inputString = Long.toString(checkNum);

            ArrayList<String> checkForPatterns =new ArrayList<>();
            
            //Check if pattern length is valid for whole string

            for(int patternLength = 1; (patternLength <= inputString.length()/2);patternLength++){
                checkForPatterns.clear();
                
                if(inputString.length()%patternLength != 0)
                    continue;

                // Fill check with consecutive substrings of equal length
                for (int j = 0; j*(patternLength)+1 <= inputString.length(); j++){
                    checkForPatterns.add(inputString.substring(j*patternLength, j*(patternLength)+patternLength));
                }


                int matches = 0;

                for(String pattern : checkForPatterns){
                    if (pattern.equals(checkForPatterns.get(0)))
                        matches++;
                }

                if(matches == checkForPatterns.size())
                    return true;
                
            }


            return false;
        }
    }
}
