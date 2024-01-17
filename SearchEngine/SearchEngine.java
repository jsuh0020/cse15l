import java.io.IOException;
import java.net.URI;
import java.util.ArrayList; 

class Handler implements URLHandler {
    ArrayList<String> wordList = new ArrayList<>();
    String finalString;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            finalString = "";
            for(int i = 0; i < wordList.size(); i++){
                finalString += wordList.get(i);
                if(i != wordList.size() - 1){
                    finalString += " and ";
                }
            }
            return String.format(finalString);
        } else {
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    wordList.add(parameters[1]);
                    return String.format("Successfully added " + parameters[1] + " to word list!");
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
