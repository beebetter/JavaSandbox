import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URLEncoder;

public class Translator {
    public static void main(String [] args){
        System.out.println("Hello");
        try {
            String head = "https://translate.google.com.ua/?hl=ru#ru/en/";
            String text = "Тёмный рыцарь";
            String encodedText = URLEncoder.encode(text, "UTF-8");
            String link = head + encodedText;
            Document doc = Jsoup.connect(link).userAgent("Mozilla").timeout(10 * 1000).get();
            String selector = "#result_box > span";
            String translatedText = doc.select(selector).text();
            System.out.println(translatedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Translate translate = new Translate();

// Translating from English to Portuguese
        //String text = translate.execute("I love cookies", Language.ENGLISH, Language.PORTUGUESE);

// Printing the source and the translated texts
        //System.out.println("Original text..: " + translate.getSourceText());
        //System.out.println("Translated text: " + text);
        System.out.println("Bye, bye!");
    }
}
