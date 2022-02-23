
/*
Milestone 3
Your program should take in the name of the file and a single query of any type, and print all the lines that match that single query.
*/


import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class StringSearch{
    /*
    The main method should expect 3 command-line arguments:
    $ java StringSearch "<file>" "<query>" "<transform>"
    The overall goal of StringSearch is to take a file of text, search for lines in the file based on some queries, then print out the matching lines after transforming them somehow.
    */
    public static void main(String[] args) throws IOException{

        if (args.length == 0) {
            System.out.println("Enter something smh");
            return;
        }
        String contents = Files.readString(Paths.get(args[0]));
        String[] lines_array = contents.split("\n");
        List<String> lines = new ArrayList<String>();
        lines = Arrays.asList(lines_array);

        if (args.length >= 2) { //if query(s) given
            lines = query_processor(args[1], lines); //later edit this to complex_query (including &) method call
        }

        print_lines(lines);
        return;
    }//end of main

    static List<String> query_processor(String query, List<String> lines) {

        Query q = new Query();

        //can't do if(query.contains("contains") && !(query.contains("not"))) because keyword could also be "not"
        if (query.startsWith("contains")) {
            String keyword = query.substring(query.indexOf("=") + 2, query.length() - 1); //+2 to account for starting ' and -1 to account for ending '
            return q.contains(lines, keyword); 
        }
        if (query.startsWith("length")) {
            int len = Integer.parseInt(query.substring(query.indexOf("=") + 1, query.length()));
            return q.length(lines, len);
        }
        if (query.startsWith("greater")) {
            int len = Integer.parseInt(query.substring(query.indexOf("=") + 1, query.length()));
            return q.greater(lines, len);
        }
        if (query.startsWith("less")) {
            int len = Integer.parseInt(query.substring(query.indexOf("=") + 1, query.length()));
            return q.less(lines, len);
        }
        if (query.startsWith("starts")) {
            String keyword = query.substring(query.indexOf("=") + 2, query.length() - 1); //+2 to account for starting ' and -1 to account for ending '
            return q.starts(lines, keyword); 
        }
        if (query.startsWith("ends")) {
            String keyword = query.substring(query.indexOf("=") + 2, query.length() - 1); //+2 to account for starting ' and -1 to account for ending '
            return q.ends(lines, keyword); 
        }
        if (query.startsWith("not")) { 
            String inner_query = query.substring(query.indexOf("(") + 1, query.indexOf(")"));
            List<String> inner_query_result = query_processor(inner_query, lines);
            //lines.removeAll(inner_query_result); //there can be multiple of the same line in a file, so use removeAll()
            //return lines;
            List<String> result = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                if (inner_query_result.indexOf(lines.get(i)) == -1) {
                    result.add(lines.get(i));
                }
            }
            return result;
        }

        return lines;
    }//end of query_processor

    static void print_lines(List<String> res) {
        for (String line : res) {
            System.out.println(line);
        }
    }//end of print_lines()

}//end of StringSearch class




class Query{

    List<String> contains(List<String> input_lines, String keyword) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < input_lines.size(); i++) {
            if (input_lines.get(i).contains(keyword)) {
                result.add(input_lines.get(i));
            }
        }
        return result;
    }

    List<String> length(List<String> input_lines, int len) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < input_lines.size(); i++) {
            if (input_lines.get(i).length() == len) {
                result.add(input_lines.get(i));
            }
        }
        return result;
    }

    List<String> greater(List<String> input_lines, int len) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < input_lines.size(); i++) {
            if (input_lines.get(i).length() > len) {
                result.add(input_lines.get(i));
            }
        }
        return result;
    }

    List<String> less(List<String> input_lines, int len) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < input_lines.size(); i++) {
            if (input_lines.get(i).length() < len) {
                result.add(input_lines.get(i));
            }
        }
        return result;
    }

    List<String> starts(List<String> input_lines, String keyword) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < input_lines.size(); i++) {
            if (input_lines.get(i).startsWith(keyword)) {
                result.add(input_lines.get(i));
            }
        }
        return result;
    }

    List<String> ends(List<String> input_lines, String keyword) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < input_lines.size(); i++) {
            if (input_lines.get(i).endsWith(keyword)) {
                result.add(input_lines.get(i));
            }
        }
        return result;
    }

}//end of Query class


