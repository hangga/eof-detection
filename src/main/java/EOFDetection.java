import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EOFDetection {

    public void readWithFIleInputStream(String pathFile, OnDetectionListener listener){
        try {
            FileInputStream fis = new FileInputStream(pathFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int data;

            while ((data = fis.read()) != -1) {
                char character = (char) data;
                baos.write(character);
            }

            fis.close();
            listener.onSucceeded(baos.toString());
        } catch (IOException e) {
            listener.onFailed(e.getMessage());
        }
    }

    public void readWithBufferedReader(String pathFile, OnDetectionListener onDetectionListener){
        try (FileInputStream fis = new FileInputStream(pathFile);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)) {

            StringBuilder actualContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                actualContent.append(line);
            }

            onDetectionListener.onSucceeded(actualContent.toString());
        } catch (IOException e) {
            onDetectionListener.onFailed(e.getMessage());
        }
    }

    public void readWithScanner(String pathFile, OnDetectionListener onDetectionListener){

        try (FileInputStream fis = new FileInputStream(pathFile); Scanner scanner = new Scanner(fis)) {
            scanner.useDelimiter("\\A"); // Menggunakan delimiter agar membaca seluruh isi file

            String actualContent = scanner.hasNext() ? scanner.next() : ""; // Membaca seluruh isi file

            onDetectionListener.onSucceeded(actualContent);
        } catch (IOException e) {
            onDetectionListener.onFailed(e.getMessage());
        }
    }

    public void readFileWithFileChannelAndByteBuffer(String pathFile, OnDetectionListener onDetectionListener){
        try (FileInputStream fis = new FileInputStream(pathFile); FileChannel channel = fis.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate((int) channel.size());
            channel.read(buffer);
            buffer.flip();

            String actualContent = StandardCharsets.UTF_8.decode(buffer).toString();
            onDetectionListener.onSucceeded(actualContent);
        } catch (IOException e) {
            onDetectionListener.onFailed(e.getMessage());
        }
    }
}
