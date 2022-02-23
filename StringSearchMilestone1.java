
/*
Milestone 1
Your program should take in one argument that is the name of the file to read, and print out all the lines in that file in order.
*/


import java.io.IOException;
import java.nio.file.*;

class StringSearch{

    public static void main(String[] args) throws IOException{
        //String filename = args[0];
        //String filepath = "/Users/aammya/CSE11/cse11-pa7-starter-main/" + filename;
        String contents = Files.readString(Paths.get(args[0]));
        System.out.println(contents);

    }
}
