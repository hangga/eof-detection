import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestEOFDetect {

    String pathToFile = "sample.txt"; // init sample file path

    @Test
    public void prepareFileForTest() {
        File file = new File(pathToFile);

        if (!file.exists()) {
            try {
                // Buat file baru
                file.createNewFile();
                System.out.println("File baru berhasil dibuat.");

                // Tulis konten Lorem Ipsum ke dalam file
                FileWriter writer = new FileWriter(file);
                writer.write(Const.LOREM_IPSUM);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testWithFileInputStream() {
        new EOFDetection().readWithFIleInputStream(pathToFile, new OnDetectionListener() {
            @Override
            public void onSucceeded(String actualContent) {
                assertEquals(Const.LOREM_IPSUM, actualContent);
            }

            @Override
            public void onFailed(String errMsg) {
                fail("IOException occurred: " + errMsg);
            }
        });
    }

    @Test
    public void testReadFileWithBufferedReader() {
        new EOFDetection().readWithBufferedReader(pathToFile, new OnDetectionListener() {
            @Override
            public void onSucceeded(String actualContent) {
                assertEquals(Const.LOREM_IPSUM, actualContent);
            }

            @Override
            public void onFailed(String errMsg) {
                fail("IOException occurred: " + errMsg);
            }
        });
    }

    @Test
    public void testReadFileWithScanner() {
        new EOFDetection().readWithScanner(pathToFile, new OnDetectionListener() {
            @Override
            public void onSucceeded(String actualContent) {
                assertEquals(Const.LOREM_IPSUM, actualContent);
            }

            @Override
            public void onFailed(String errMsg) {
                fail("IOException occurred: " + errMsg);
            }
        });
    }

    @Test
    public void testReadFileWithFileChannelAndByteBuffer() {
        new EOFDetection().readFileWithFileChannelAndByteBuffer(pathToFile, new OnDetectionListener() {
            @Override
            public void onSucceeded(String actualContent) {
                assertEquals(Const.LOREM_IPSUM, actualContent);
            }

            @Override
            public void onFailed(String errMsg) {
                fail("IOException occurred: " + errMsg);
            }
        });
    }

}
