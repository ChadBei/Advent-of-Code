import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        System.out.println("The dial starts by pointing at 50.");

        String filePath = "Day1/input.txt";
        String[] fileContent = FileReaderToArray.readFileToArray(filePath);
        File myFile = new File(filePath);
        System.out.println(myFile.getAbsolutePath());

        Dial myDial = new Dial();

        int passcode = 0;
    
        for (String line : fileContent) {
            ////// Commenting out original method, switching to brute force count
            // char direction = line.charAt(0);
            // int turnAmt = Integer.parseInt(line.substring(1));
            // int startPos = myDial.currentPosition;
            // int endPos = myDial.turnDial(direction, turnAmt%100);
            
            
            // if (endPos == 0) {
            //     passcode++;
            // }

            // passcode += turnAmt/100;

            // System.out.print("The dial is rotated " + line + " to point at " + endPos);
            // if(direction == 'R')
            //     if ((endPos < startPos) && (endPos != 0)){
            //         passcode++;
            //     }

            // if(direction == 'L'){
            //     if ((endPos > startPos)&& (startPos != 0)){
            //         passcode++;
            //     } else if ((endPos < startPos)){
            //         passcode++;
            //     }
            // }

            // System.out.println(".");





            ////// Bruteforce Method below

            char direction = line.charAt(0);
            int turnAmt = Integer.parseInt(line.substring(1));
            passcode += myDial.bruteTurnDial(direction, turnAmt);


        }

        System.out.println("The passcode is: " + passcode);

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

    public static class Dial {
        public int currentPosition;

        // Returns new position after 
        public int turnDial(char direction, int turnAmt){
            if(direction == 'L'){
                currentPosition -= turnAmt;
                if(currentPosition < 0){
                    currentPosition += 100;
                }
                return currentPosition;
            } else{
                currentPosition += turnAmt;
                if(currentPosition > 99){
                    currentPosition -= 100;
                }
                return currentPosition;
            }
        }

        public int bruteTurnDial(char direction, int turnAmt){
            int numZero = 0;
            int position = currentPosition;

            // For loop will move dial 1 click each iteration
            for(int i= turnAmt; i > 0; i--){
                position = bruteTurnOne(direction, position);

                if (position == 0)
                    numZero++;
            }
            currentPosition = position;

            return numZero;

        }

        private int bruteTurnOne(char direction, int current){

            if (direction == 'L') 
                current--;

            if (direction == 'R')
                current++;

            if (current > 99)
                return 0;

            if (current < 0)
                return 99; 

            return current;
        }

        public Dial(){
            this.currentPosition = 50;
        }
    }

}