package Day4_a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4_a{
    public static void main(String[] args) throws FileNotFoundException {

        //Read input into 2d char array
        String filePath = "Day4_a/input.txt";
        ArrayList<String> lines = new ArrayList<>();
        int numLines=0;

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
            numLines++;
        }
        scanner.close();

        char[][] paperMap = new char[lines.get(0).length()][numLines];

        for(int i = 0;i<lines.get(0).length(); i++){
            for(int j = 0; j<numLines;j++){
                paperMap[i][j]=lines.get(i).charAt(j);
                // System.out.print(lines.get(i).charAt(j));
            }
            // System.out.println();
        }

        boolean isTopEdge = false;
        boolean isRightEdge = false;
        boolean isLeftEdge = false;
        boolean isBottomEdge = false;

        int passcode = 0;

        for (int i = 0; i < paperMap[0].length; i++) {

            for (int j = 0; j < numLines; j++) {

                // Skip char if not paper
                if(paperMap[i][j] != '@')
                    continue;

                int touchCount =0;    

                // Itendify all edges
                int rStart=-1;
                int cStart=-1;
                int rEnd=1;
                int cEnd=1;
   

                if(i == 0)
                    rStart=0;
                if(i == paperMap[i].length-1)
                    rEnd=0;
                if(j==0)
                    cStart=0;
                if(j == numLines-1)
                    cEnd=0;    


                for(int r = rStart; r<=rEnd;r++){
                        for(int c = cStart; c<=cEnd; c++){
                            if(paperMap[i+r][j+c] == '@')
                                touchCount++;
                            if(r==0 && c==0)
                                touchCount--;    
                        }
                    }

                if(touchCount < 4)
                    passcode++;
            }
        }

        System.err.println("Passcode is " + passcode);

    }
}