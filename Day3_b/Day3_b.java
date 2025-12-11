package Day3_b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3_b {
    public static void main(String[] args) {

        String filePath = "Day3_a/input.txt";
        String[] fileContent = FileReaderToArray.readFileToArray(filePath);


        long totalJoltage=0;
        for(String i : fileContent){
            long jolt= Battery.getJoltage(i);
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
        public static long getJoltage(String batPack){

            // int fDigit=batPack.charAt(0)-'0';
            // int fIndex=0;

            //Find the largest digit in the pack (can't be last digit)
            // for(int i=0; i<batPack.length()-1; i++){
            //     if(fDigit<batPack.charAt(i)-'0'){
            //         fDigit = batPack.charAt(i)-'0';
            //         fIndex=i;
            //     }
            // }

            // int lDigit=batPack.charAt(fIndex+1)-'0';
            // int lIndex=fIndex+1;

            // for(int i=lIndex; i<batPack.length(); i++){
            //     if(lDigit<batPack.charAt(i)-'0'){
            //         lDigit = batPack.charAt(i)-'0';
            //         lIndex=i;
            //     }
            // }

            // return (fDigit*10) + lDigit;

            final int JOLTAGE_LENGTH = 12;
            int[] batValues = new int[JOLTAGE_LENGTH];
            int[] batIndexes = new int[JOLTAGE_LENGTH];
            
            batIndexes[0] = Battery.getIndexOfLargestNum(batPack.substring(0, batPack.length()-JOLTAGE_LENGTH));
            batValues[0] = batPack.charAt(batIndexes[0]) - '0';
            for(int i = 1;i<JOLTAGE_LENGTH;i++){

                int temp = Battery.getIndexOfLargestNum(batPack.substring(batIndexes[i-1]+1, batPack.length()-(JOLTAGE_LENGTH - i-1)));
                batValues[i] = batPack.substring(batIndexes[i-1]+1, batPack.length()-(JOLTAGE_LENGTH-i-1)).charAt(temp)-'0';
                batIndexes[i] = batIndexes[i-1] + temp +1;

            }
            
            String joltage = "";

            for(int i : batValues){
                joltage +=i;
            }

           return Long.parseLong(joltage);
        }

        public static int getIndexOfLargestNum(String bigNum){
            int largestNum = bigNum.charAt(0) - '0';
            int largestNumIndex=0;

            int temp = 0;
            for(char c : bigNum.toCharArray()){
                if(largestNum < c -'0'){
                    largestNum = c -'0';
                    largestNumIndex = temp;
                }
                temp++;
            }
        return largestNumIndex;

        }

    }



}

