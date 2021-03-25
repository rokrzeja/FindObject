package FindPackage;

import java.util.Arrays;

public class Test {

    public static void main (String [] args){

        FindFile findInFile = new FindFile("C:\\Users\\HP\\Desktop\\plik.txt");

        show(findInFile.returnMatchingWords("[a-z0-9]{2,20}@[a-z0-9]{2,4}.[a-z]{2,5}"));
        show(findInFile.returnMatchingWords("[0-9]{1,6}"));

        show(Arrays.toString(findInFile.returnEvenNumbers().toArray()));
        show(Arrays.toString(findInFile.returnOddNumbers().toArray()));

        show("Min: " + findInFile.returnMinNumber());
        show("Max: " + findInFile.returnMaxNumber());

        FindHtml findInHtml = new FindHtml("http://api.openweathermap.org/data/2.5/weather?q=Warsaw,Poland&appid=d3bcfb30c88069416148c70a61ea513e");
        show(findInHtml.returnFullHtmlBody());

        show(findInHtml.returnMatchingWords("[0-9]{1,6}"));
        show(findInHtml.returnResponseHeader("Server"));

        show(findInFile.returnContext("return"));
        show(findInHtml.returnContext("coo"));

    }

    public static void show(Object toPrint){

        System.out.println(toPrint);
    }

}
