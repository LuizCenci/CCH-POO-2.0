package po23s.App;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class utils{
    public static String encoder(String txt){ //Cria uma função que trata um encode
        String txtEncode;
        try {
            txtEncode= URLEncoder.encode(txt.trim(), "UTF-8");
            return "https://www.googleapis.com/books/v1/volumes?q="+txtEncode+"&key=AIzaSyBFL7oexO7Z_k2smvnlYe3jRLY93m2OjuI";
        } catch (UnsupportedEncodingException e) {//Se a geração do url falhar retorna um texto vazio
            return "";
        }
    }
    
}
