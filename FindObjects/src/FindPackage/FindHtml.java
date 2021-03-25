package FindPackage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindHtml implements Find{

    private HttpClient clientHttp;
    private HttpRequest requestHttp;
    private HttpResponse<String> responseHttp;

    public FindHtml(String uri){

        clientHttp = HttpClient.newHttpClient();
        requestHttp = HttpRequest.newBuilder()
                        .uri(URI.create(uri)).build();
        try {
            responseHttp = clientHttp.send(requestHttp, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashSet returnMatchingWords(String regex){

        HashSet uniqueWords = new HashSet();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(responseHttp.body());

        while(matcher.find()){
            uniqueWords.add(matcher.group());
        }

        return uniqueWords;
    }

    @Override
    public String returnContext(String txt) {

        String body = returnFullHtmlBody();
        Pattern pattern = Pattern.compile(txt);
        Matcher match = pattern.matcher(body);

        List<String> contexts = new ArrayList<String>();
        String context;
        int start=0;

        while(match.find()){

            context="";
            if(match.start()-20 > 0) start = match.start();

            for(int i=start; i<match.end()+20 && i<body.length() ; i++){
                context += body.charAt(i);
            }
            contexts.add(context);
        }

        if(contexts.size() != 0) return contexts.toString();
        else return "Context not found";
    }

    public String returnResponseHeader(String header){

        return responseHttp.headers().allValues(header).toString();
    }

    public String returnFullHtmlBody(){
        return responseHttp.body();
    }

}
