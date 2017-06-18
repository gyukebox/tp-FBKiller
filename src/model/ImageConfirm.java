package model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class ImageConfirm {
    private static String context = null;

    private ImageConfirm() {

    }

    public static String confirm(String urlInput, ArrayList<String> bannedWords) {
        boolean ban = false;
        @SuppressWarnings("deprecation")
        HttpClient httpclient = new DefaultHttpClient();
        @SuppressWarnings("deprecation")
        HttpClient TexthttpClient = new DefaultHttpClient();

        try {
            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/analyze");
            URIBuilder Textbuilder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/ocr");

            builder.setParameter("visualFeatures", "Adult");
            builder.setParameter("language", "en");

            Textbuilder.setParameter("language", "unk");
            Textbuilder.setParameter("detectOrientation ", "true");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            URI Texturi = Textbuilder.build();
            HttpPost Textrequest = new HttpPost(Texturi);

            // Request headers.
            request.setHeader("Content-Type", "application/json");
            Textrequest.setHeader("Content-Type", "application/json");

            // NOTE: Replace the example key with a valid subscription key.
            request.setHeader("Ocp-Apim-Subscription-Key", "ba5dc9510d13453c9773c760e14d79ab");
            Textrequest.setHeader("Ocp-Apim-Subscription-Key", "a6a2343c2ca64f948e4828ddc62c47b3");

            // Request body. Replace the example URL with the URL for the JPEG image of a celebrity.
            StringEntity reqEntity = new StringEntity("{" + "\"" + "url" + "\"" + ":\"" + urlInput + "\"" + "}");//"{\"url\":\"http://4.bp.blogspot.com/-iiU3ZnMhQSw/Vbb5MMhEJjI/AAAAAAAAAEo/bb7_YPahbkE/s1600/%25ED%2594%258C%25EB%259E%259C1.jpg\"}"
            request.setEntity(reqEntity);
            Textrequest.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            HttpResponse Textresponse = TexthttpClient.execute(Textrequest);
            HttpEntity Textentity = Textresponse.getEntity();

            if (entity != null) {
                String str = EntityUtils.toString(entity);
                str = TextConfirm.deleteSC(str);
                if (str.contains("isAdultContenttrue")) {
                    ban = true;
                    System.out.println("explicit content!");
                    context += "[광고] ";
                } else {
                    System.out.println("not an explicit content.");
                }

                if (str.contains("isRacyContenttrue")) {
                    ban = true;
                    System.out.println("sexual content!");
                    context += "[19금] ";
                } else {
                    System.out.println("not a sexual content.");
                }
            }

            if (Textentity != null) {
                String Str = EntityUtils.toString(Textentity);
                Str = TextConfirm.deleteSC(Str);

                if (TextConfirm.checkBlackWord(Str, bannedWords)) {
                    ban = true;
                    context += "[금지어] ";
                }
            }

            if (ban) {
                context = "게시물 차단 이유 : " + context;
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(context);
        return context;
    }
}