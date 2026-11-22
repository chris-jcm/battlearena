package test;

import battlearena.server.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import java.util.List;

public class FileServiceTest {
    //TDD
    @TempDir
    Path tempDir;
    FileService fileService;

    @BeforeEach
    void setUp(){
        fileService = new FileService(tempDir.resolve("history.txt"));
    }

    @Test
    void readAll() throws Exception {
        List<String> history = fileService.readAll();
        assertTrue(history.isEmpty());
    }

    @Test
    void writeLog() throws Exception {
        String record = "Name|WARRIOR|WARRIOR|LOSS";
        fileService.writeToFile(record);
        assertEquals(List.of(record), fileService.readAll());
    }

    @Test
    void writeSeveralLog() throws Exception {
        String record = "Name|WARRIOR|WARRIOR|LOSS";
        String record2 = "Nanny|MAGE|ROUGE|WIN";
        fileService.writeToFile(record);
        fileService.writeToFile(record2);
        assertEquals(List.of(record, record2), fileService.readAll());
    }
}
