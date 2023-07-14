import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GetDataFromURL {
    public String getData() throws IOException {
        URL url = new URL("https://dummyjson.com/products");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("X-Cons_ID", "1234567");
        urlConnection.setRequestProperty("user_key", "faY738sH");
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            } else {
                return  null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
    }
}
