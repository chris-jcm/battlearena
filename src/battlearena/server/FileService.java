package battlearena.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileService {
    public void writeToFile() {

    }

    public List<String> readAll() throws IOException {
        return Files.readAllLines(Path.of("filename"));
    }
}
