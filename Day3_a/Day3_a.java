package Day3_a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3_a {
    public static void main(String[] args) {

        String filePath = "Day3_a/input.txt";
        String[] fileContent = FileReaderToArray.readFileToArray(filePath);


        int totalJoltage=0;
        for(String i : fileContent){
            int jolt= Battery.getJoltage(i);
            System.out.println("Joltage is " + jolt+ " for " + i);
            totalJoltage+=jolt;
        }
        System.out.println("Total Joltage is " + totalJoltage);

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

    public static class Battery {
        public static int getJoltage(String batPack){

            int fDigit=batPack.charAt(0)-'0';
            int fIndex=0;

            //Find the largest digit in the pack (can't be last digit)
            for(int i=0; i<batPack.length()-1; i++){
                if(fDigit<batPack.charAt(i)-'0'){
                    fDigit = batPack.charAt(i)-'0';
                    fIndex=i;
                }
            }

            int lDigit=batPack.charAt(fIndex+1)-'0';
            int lIndex=fIndex+1;

            for(int i=lIndex; i<batPack.length(); i++){
                if(lDigit<batPack.charAt(i)-'0'){
                    lDigit = batPack.charAt(i)-'0';
                    lIndex=i;
                }
            }

            return (fDigit*10) + lDigit;
        }
    }



}

