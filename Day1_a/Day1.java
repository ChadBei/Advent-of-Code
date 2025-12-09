
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {
        System.out.println("The dial starts by pointing at 50.");

        String filePath = "input.txt";
        String[] fileContent = FileReaderToArray.readFileToArray(filePath);

        Dial myDial = new Dial();

        int passcode = 0;
    
        for (String line : fileContent) {
            
            char direction = line.charAt(0);
            int turnAmt = Integer.parseInt(line.substring(1));
        
            System.out.println("The dial is rotated " + line + " to point at " + myDial.turnDial(direction, turnAmt%100) + ".");
            if (myDial.currentPosition == 0)
                passcode++;
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

        public int turnDial(char direction, int turnAmt){
            if(direction == 'L'){
                currentPosition -= turnAmt;
                if(currentPosition < 0){
                    currentPosition += 100;
                }
                return currentPosition;
            } else if(direction == 'R'){
                currentPosition += turnAmt;
                if(currentPosition > 99){
                    currentPosition -= 100;
                }
                return currentPosition;
            }
            return -1;
        }

        public Dial(){
            currentPosition = 50;
        }
    }

}