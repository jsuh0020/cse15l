import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    String listOfMessages = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return listOfMessages;
        }
        if (url.getPath().equals("/add-message")) {
            String[] parameters = url.getQuery().split("&");
            String[] messages = parameters[0].split("=");
            String[] usernames = parameters[1].split("=");
            if (usernames.length == 1) {
                return "No User Provided";
            }
            if (messages.length == 1) {
                return "No Message Provided";
            }
            if (messages[0].equals("s") && usernames[0].equals("user")) {
                listOfMessages += usernames[1] + ": " + messages[1] + "\n";
            }
            return listOfMessages;
        }
        return "404 Not Found!";
    }
}

class ChatServer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
