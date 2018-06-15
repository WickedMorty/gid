package other;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadFileFromUrl {
    public static boolean DownloadFileFromUrl(String fileUrl, String outputName) {
        try {
            URL website = new URL(fileUrl);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(outputName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
