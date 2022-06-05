package download;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.FileOutputStream;

public class DownloadUtil {
    public static void downloadFile(String filename,String link){
        System.out.println("Start download ");
        try {
            Connection.Response response = Jsoup.connect(link).ignoreContentType(true).execute();
            FileOutputStream out = new FileOutputStream(filename);
            out.write(response.bodyAsBytes());
            out.close();
            System.out.printf("Download %s success /n",filename);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
