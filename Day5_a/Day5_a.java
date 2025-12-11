package Day5_a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day5_a {
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

        ArrayList<Long> rangesList = new ArrayList<>();
        for(int i =0; i<lines.indexOf(""); i++){
            for(String s : lines.get(i).split("-"))
                rangesList.add(Long.valueOf(s));
        }

        ArrayList<Long> idList = new ArrayList<>();
        for(int i =lines.indexOf("")+1; i < lines.size(); i++){
            idList.add(Long.valueOf(lines.get(i)));
        }

        int passcode =0;

        for(Long id : idList){
            for(int i = 0; i<rangesList.size()/2; i++){
                if(id <= rangesList.get(i*2+1) && id >= rangesList.get(i*2)){
                    passcode++;
                    break;
                }
            }
        }

        System.out.println("Passcode is: " + passcode);
        return;



    }
}
