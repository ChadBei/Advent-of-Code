package Day5_b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5_b {
     public static void main(String[] args) throws FileNotFoundException {
         String filePath = "Day5_a/input.txt";
        ArrayList<String> lines = new ArrayList<>();
        int numLines=0;

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
            numLines++;
        }
        scanner.close();

        ArrayList<String> newRangeList = new ArrayList<>();
        ArrayList<Long> rangesList = new ArrayList<>();

        for(int i =0; i<lines.indexOf(""); i++){
            for(String s : lines.get(i).split("-"))
                rangesList.add(Long.valueOf(s));
        }

        RangeFinder rangeFinder = new RangeFinder();
        rangeFinder.myRanges.add(rangesList.get(0));
        rangeFinder.myRanges.add(rangesList.get(1));
        for(int i = 0; i<rangesList.size()/2; i++){

            // If left and right end of ranges is not in ranges
            if(!rangeFinder.isInMyRanges(rangesList.get(i*2)) && !rangeFinder.isInMyRanges(rangesList.get(i*2+1))){
                rangeFinder.myRanges.add(rangesList.get(i*2));
                rangeFinder.myRanges.add(rangesList.get(i*2+1));
            }
        }

        int passcode = 0;

        System.out.println("Passcode is: " + passcode);
        return;
    }



    public static class RangeFinder{
        public ArrayList<Long> myRanges;

        public RangeFinder(){
            myRanges = new ArrayList<>();
        }

        public boolean isInMyRanges(Long in){
            for(int i = 0; i<myRanges.size()/2; i++){
                if(in <= myRanges.get(i*2+1) && in >= myRanges.get(i*2))
                    return true;
            }
            return false;
        }
    }
}
