import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
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
        String text = "Russia Threatens Retaliation for Ukraine’s Drill near Crimea. Text of the Diplomatic Cable" +
                "Ukrayinska Pravda had obtained a document confirming threats of the Russian Ministry of Defence to launch an air attack on Ukraine, after Ukraine had announced its plans to conduct a military exercise near Crimea [Ukrainian territory which is currently illegally occupied by the Russian military].\n" +
                "UP had obtained a copy of the diplomatic cable by the Russian Ministry of Defence, addressed to the Office of the Military Attache of the Ukrainian Embassy in Moscow.\n" +
                " \n" +
                "UP had obtained a copy of the diplomatic cable by the Russian Ministry of Defence\n" +
                "\"The Ministry of Defence of the Russian federation cautions, that if the rockets are launched within the indicated area, they will be destroyed by the air defence forces of the Russian Federation,\" — states the cable.\n" +
                "\"Should these rockets create danger for the Russian objects, located within the stated Russian territory (be it ground, sea of air), there will be military strikes to destroy the equipment used in the launches,\" — the cable continues.\n" +
                "As it was already reported by Dzerkalo Tyzhnia, with reference to the Ministry of Defence of Ukraine, Russia threats a retaliation strike if Ukraine conducts a military excersice involving rocket launches in the vicinity of the administrative border of Crimea. The information was later confirmed by the Ukrainian diplomats.\n" +
                "Ealier Russian Ministry of Defence had invited Ukrainian military attache to hand over a diplomatic note, explaining Russian reaction to Ukraine’s planned military excercise.\n" +
                "Replying to the Russian MoD, Oleksandr Turchinov, the Secretary of the Security and Defence Council of Ukraine, asked Russians not to panic.\n" +
                "He stressed that Ukraine is conducting its military excersice within its sovereign territory and air space in accordance with all the obligations it had undertaken under the relevant international treaties.\n" +
                "Dmitri Peskov, the spokesman for the Russian President Vladimir Putin, could not answer a question, whether his boss is planning a military response to Ukraine’s launches.";
        String title = "Russia Threatens Retaliation for Ukraine’s Drill near Crimea. Text of the Diplomatic Cable";
        String text2 = "\"Министерство обороны РФ предупреждает, что при обнаружении стартовавших ракет в указанном районе они будут уничтожаться силами противовоздушной обороны Вооруженных Сил РФ\", - сказано в письме.\n" +
                "\"В случае если стартовавшие ракеты создадут угрозу российским объектам, находящимся в данном районе территории РФ (на суше, на море и в воздушном пространстве), будут нанесены ответные удары для уничтожения средств их пуска\", - добавили в Минобороны России.\n" +
                "Как известно, издание DT.UA, ссылаясь на источники в Минобороны Украины, сообщило, что Россия угрожает ракетным ударом в случае проведения Украиной ракетных стрельб в районе админграницы в Крыму. Позже эту информацию подтвердили украинские дипломаты.\n" +
                "Минобороны РФ накануне пригласило атташе по вопросам обороны при посольстве Украины в РФ для вручения военно-дипломатической ноты в связи с планами Украины провести ракетные стрельбы рядом с Крымом.\n" +
                "\n" +
                "В ответ секретарь СНБО Александр Турчинов попросил РФ не паниковать из-за ракетных испытаний Украины.\n" +
                "\n" +
                "Он подчеркнул, что ракетные испытания Украина будет проводить согласно всем международным обязательствам и договорам в своем суверенном воздушном пространстве.\n" +
                "В то же время пресс-секретарь президента РФ Дмитрий Песков не смог ответить на вопрос, поручал ли российский лидер Владимир Путин Минобороны РФ подготовить военный ответ на возможные пуски ракет Украиной.";
        String title2 = "Появился текст угроз России из-за украинских учений возле Крыма";
        String url = "http://www.pravda.com.ua/news/2017/05/31/7145603/";
        String source = "pravda.com.ua";
        baseData.add(new BaseText(text2, title2, url, source));
        StanfordTonality stanfordTonality = new StanfordTonality();
        stanfordTonality.calculate(baseData);
        System.out.println(baseData.get(0).getBtNeuralTonality());
    }
}