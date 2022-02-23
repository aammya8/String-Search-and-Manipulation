
/*
Milestone 2
Your program should take in the name of the file and a single contains query, and print all the lines that match that single contains query.
*/


import java.io.IOException;
import java.nio.file.*;

class StringSearch{

    public static void main(String[] args) throws IOException{
        String contents = Files.readString(Paths.get(args[0]));
        String[] lines = contents.split("\n");

        //later do if (args[1].contains("contains") && INDEXOF(  ) == _____)
        if (args[1].contains("contains")) {
            String keyword = args[1].substring(args[1].indexOf("=") + 2, args[1].length() - 1); //+2 to account for starting ' and -1 to account for ending '
            //System.out.println(keyword);
            for (String line : lines) {
                if (line.contains(keyword)) {
                    System.out.println(line);
                }
            }
            

        }
        return;

    }//end of main
}
