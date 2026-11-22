package battlearena.server;
import battlearena.client.ArenaClient;

import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.util.List;

public class ArenaServer {

    private static final int PORT = 5001;

    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService(Path.of("battle_history.txt"));

        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Arena Server Started on port " + PORT);

            while(true){
                try(Socket client = serverSocket.accept()){
                    System.out.println("Client connected: " + client.getInetAddress());

                    requestReader(client, fileService);

                } catch (IOException e) {
                    System.out.println("Error accepting client connection");
                }
            }
        } catch (IOException e){
            System.err.println("Couldn't start server: " + e);
        }
    }

    private static void requestReader(Socket client, FileService fileService) throws IOException {
        var requestReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String request = requestReader.readLine();

        if (request == null) {
            return;
        }

        if (ArenaClient.GET_HISTORY.equals(request)) {
            System.out.println("Client wants to get history");
            List<String> history = fileService.readAll();
            var response = new PrintWriter(client.getOutputStream(), true);

            for(String record : history){
                response.println(record);
            }

        } else {
            System.out.println("Client wants to write history");
            fileService.writeToFile(request);
        }

    }


}
