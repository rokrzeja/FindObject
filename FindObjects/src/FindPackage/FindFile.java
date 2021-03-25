package FindPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFile implements Find{

    private File file;
    private FileInputStream reader;

    public FindFile(String filepath) {
        file = new File(filepath);
        try {
            this.reader = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashSet returnMatchingWords(String regex){

        HashSet uniqueObjects = new HashSet();

        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(returnTextFromFile());

        while(match.find()) {
            uniqueObjects.add(match.group());
        }

        return uniqueObjects;
    }

    @Override
    public String returnContext(String txt) {

        String wholeTxt = returnTextFromFile();
        Pattern pattern = Pattern.compile(txt);
        Matcher match = pattern.matcher(wholeTxt);
        String contexts ="";
        int start = 0;

        while(match.find()){

           contexts += "[";
           if(match.start()-10 >= 0) start = match.start();

           for(int i=start; i<match.end()+10 && i<wholeTxt.length() ; i++){
               contexts += wholeTxt.charAt(i);
           }
            contexts += "]";
        }

        if(contexts.equals("")) return "Context not found";
        else return contexts;
    }

    public List<Integer> returnEvenNumbers(){

        List <Integer> evenNumbers = returnListOfNumbers();

        for (int i = 0; i< evenNumbers.size(); i++) {
            if(evenNumbers.get(i) % 2 != 0){
                evenNumbers.remove(i);
                i--;
            }
        }

        return evenNumbers;
    }

    public List<Integer> returnOddNumbers(){

        List <Integer> oddNumbers = returnListOfNumbers();

        for (int i = 0; i< oddNumbers.size(); i++) {
            if(oddNumbers.get(i) % 2 == 0){
                oddNumbers.remove(i);
                i--;
            }
        }

        return oddNumbers;
    }

    public int returnMinNumber(){

        List <Integer> numbers = returnListOfNumbers();

        int min = numbers.get(0);
        for(int i=0; i< numbers.size(); i++){

            if(numbers.get(i) < min){
                min = numbers.get(i);
            }
        }

        return min;
    }

    public int returnMaxNumber(){

        List <Integer> numbers = returnListOfNumbers();

        int max = numbers.get(0);
        for(int i=0; i< numbers.size(); i++){

            if(numbers.get(i) > max){
                max = numbers.get(i);
            }
        }

        return max;
    }

    public List<Integer> returnListOfNumbers() {

        List<Integer> numbers = new ArrayList<Integer>();
        int i;
        String digit = "";

        try {
            do {
                i = reader.read();

                if (Character.isDigit((char) i)) {
                    digit += (char) i;

                } else {
                    if (!digit.equals("")) {
                        int number = Integer.parseInt(digit);
                        numbers.add(number);
                    }

                    digit = "";
                }
            } while (i != -1);
            reader.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numbers;
    }

    public String returnTextFromFile(){

        String wholeTxt="";
        try {
            int i;
            while((i = reader.read()) != -1){

                wholeTxt += (char)i;
            }
            reader.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wholeTxt;
    }






}
