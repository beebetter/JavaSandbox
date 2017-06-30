import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class StanfordTonality {
        public void calculate(List<BaseText> baseData) {
        String TranslatedText = "";
        String key = "trnsl.1.1.20170411T210633Z.9ff2ca1360839732.4a2dc75f582cee5484d3f1320728cf4c3a57f9e0";
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        for(BaseText btItem: baseData) {
            String text = btItem.getBtText();
            int sentenseSum = 0;
            Annotation annotation = (Annotation) pipeline.process(text);
            List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
            for (CoreMap sentence : sentences) {
                String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                System.out.println(sentiment);
                if (sentiment.equals("Negative") || sentiment.equals("Very negative")) {
                    sentenseSum--;
                } else if (sentiment.equals("Positive") || sentiment.equals("Very positive")) {
                    sentenseSum++;
                } else if (sentiment.equals("Neutral")) {
                } else {
                    throw new IllegalArgumentException("Invalid sentiment: " + sentiment);
                }
            }
            if(sentenseSum>0) btItem.setNeuralTonality(2);
            else if (sentenseSum==0) btItem.setNeuralTonality(1);
            else btItem.setNeuralTonality(2);
        }
    }
    public static void main (String[] args) {
        List<BaseText> baseData = new ArrayList<BaseText>();
        String text = "Text";
        String text2 = "There is no ideal Prosecutor General, like there is no ideal boyfriend. No other law-enforcement agency survived such an amount of management changes and disturbances like the Office of the Prosecutor General of Ukraine (GPU).\n" +
                "For example, Arsen Avakov of Narodnyi Front political party has been continuous leader of the Ministry of Interior since the times of Maidan. Avakov has a close grip on his ministry, yet if we analyse it thoroughly, the transformation of the Yanukovych era ‘militsiya’ into a truly modern National Ukrainian Police is also a type of foot-dragging.\n" +
                "Yet Avakov is wise enough not to start an open offensive against his ‘reformers’. At least these conflicts never appear in public.\n" +
                "The situation is radically different in the GPU. Ukraine now has the fourth Prosecutor General since Yanukovych fled, and for these two years the GPU was the source of public discontent and bad ratings for Poroshenko, while traditionally the GPU is the President’s fiefdom within the modern Ukrainian political system.\n" +
                "All internal conflicts within the GPU happened publicly. The apogee occurred at the times of Viktor Shokin, who started a personal feud with his own deputies Vitaliy Kasko and Davit Sakvarelidze.\n" +
                "After the ‘case of the diamond prosecutors’ an internal conflict within the GPU started looking more like the ‘old system’ battling the ‘reformers’. The outcome was that both teams: ‘the old ones’ and ‘the reformers’ were fired and Poroshenko appointed his close friend and Ukrainian political heavyweight Yuri Lutsenko to put out the fire.\n" +
                "The reasoning, explaining why a politician without any legal education should lead the law-enforcement agency which theoretically should have had nothing to do with politics, is clear — because of his political weight Lutsenko can resist any political influence and he is also capable of finding a compromise with all healthy factions within the GPU.\n" +
                "Yet it looks like the Prosecutor General will have to face yet another grave conflict within the Ukrainian law-enforcement system. Even if Lutsenko was not the initiator of the war between GPU and the National Anti-Corruption Bureau (NABU) — he will be the one to resolve it, which he should do before considering his return to ‘big politics’.";
        String url = "URL";
        String source = "Source";
        List<String> titles = Arrays.asList(
                "The National Guard is looking for a colonel who has disappeared in ORDLO",
                "The EU will change the design of the Schengen visa",
                "In Russia, sewing the queue of affairs to the Ukrainian military",
                "Qatar refuses any negotiations to lift the blockade");
        StanfordTonality stanfordTonality = new StanfordTonality();
        baseData.add(new BaseText(text2, "title", url, source));
        stanfordTonality.calculate(baseData);
        //stanfordTonality.test(titles);

        /*for (String title : titles) {
            baseData.add(new BaseText(text, title, url, source));
        }
        StanfordTonality stanfordTonality = new StanfordTonality();
        stanfordTonality.calculate(baseData);
        for (int i = 0; i < baseData.size(); i++) {
            System.out.println(baseData.get(i).getBtNeuralTonality());
        }*/

    }
}