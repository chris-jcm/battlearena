package battlearena.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


public class FileService {
    private final Path path;

    public FileService(Path path){
        this.path = path;
    }

    public void writeToFile(String data) throws IOException {
        String writableData = data + "\n";
        Files.writeString(path, writableData, CREATE, APPEND);
    }

    public List<String> readAll() throws IOException {
        if(Files.notExists(path)){
            return List.of();
        } else return Files.readAllLines(path);
    }
}
