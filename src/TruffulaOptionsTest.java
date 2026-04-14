import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TruffulaOptionsTest {

  @Test
  void testValidDirectoryIsSet(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-nc", "-h", directoryPath};

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert: Check that the root directory is set correctly
    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertTrue(options.isShowHidden());
    assertFalse(options.isUseColor());
  }

  @Test
  void testValidDirectoryWithHiddenOnly(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with -h and the directory path
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-h", directoryPath};

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert: Check that hidden is true and color stays true
    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertTrue(options.isShowHidden());
    assertTrue(options.isUseColor());
  }

  @Test
  void testValidDirectoryWithNoColorOnly(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with -nc and the directory path
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-nc", directoryPath};

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert: Check that hidden stays false and color is false
    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertFalse(options.isShowHidden());
    assertFalse(options.isUseColor());
  }

  @Test
  void testEmptyArgsThrowsIllegalArgumentException() {
    // Arrange: Prepare empty arguments
    String[] args = {};

    // Act and Assert: Verify exception is thrown
    assertThrows(IllegalArgumentException.class, () -> new TruffulaOptions(args));
  }

  @Test
  void testValidDirectoryWithNoFlags(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with only the directory path
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {directoryPath};

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert: Check default values
    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertFalse(options.isShowHidden());
    assertTrue(options.isUseColor());
  }

  @Test
  void testUnknownFlagThrowsIllegalArgumentException(@TempDir File tempDir) {
    // Arrange: Prepare arguments with an invalid flag
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-a", directoryPath};

    // Act and Assert: Verify exception is thrown
    assertThrows(IllegalArgumentException.class, () -> new TruffulaOptions(args));
  }

  @Test
  void testPathDoesNotExistThrowsFileNotFoundException() {
    // Arrange
    String[] args = {"directory_that_does_not_exist"};

    // Act & Assert
    assertThrows(FileNotFoundException.class, () -> new TruffulaOptions(args));
  }

  @Test
  void testPathIsFileThrowsFileNotFoundException(@TempDir File tempDir) throws Exception {
    // Arrange: create a file
    File file = new File(tempDir, "test.txt");
    file.createNewFile();

    String[] args = {file.getAbsolutePath()};

    // Act & Assert
    assertThrows(FileNotFoundException.class, () -> new TruffulaOptions(args));
  }
}
