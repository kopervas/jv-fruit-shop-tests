package core.basesyntax.service.impl;

import core.basesyntax.service.FileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FileServiceImplTest {
    private FileService fileService;
    private String testFileName;

    @BeforeEach
    public void setUp() {
        fileService = new FileServiceImpl();
        testFileName = "testFile.txt";
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(testFileName));
    }

    @Test
    public void testReadFileWithNullFilePath() {
        String nullFilePath = null;
        assertThrows(RuntimeException.class, () -> fileService.readFile(nullFilePath));
    }

    @Test
    public void testReadFileWithEmpptyFilePath() {
        String empFilePath = " ";

        assertThrows(RuntimeException.class, () -> fileService.readFile(empFilePath));
    }

    @Test
    public void testWriteToFile() {
        String content = "Test content";
        fileService.writeToFile(testFileName, content);
        assertTrue(Files.exists(Path.of(testFileName)));

        assertDoesNotThrow(() -> {
            String fileContent = Files.readString(Path.of(testFileName));
            assertEquals(content, fileContent);
        });
    }

    @Test
    public void testWriteToFileWithNullFileName() {
        String content = "Test content";
        assertThrows(RuntimeException.class, () -> fileService.writeToFile(null, content));
    }

    @Test
    public void testWriteToFileWithNullContent() {
        String fileName = "testFile.txt";
        assertThrows(RuntimeException.class, () -> fileService.writeToFile(fileName, null));
    }
}