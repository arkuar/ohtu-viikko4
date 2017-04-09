package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;


public class Main {

    public static void main(String[] args) throws IOException, NoSuchFieldException {
        String studentNr = "";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";
        String url2 = "https://ohtustats2017.herokuapp.com/courses/1.json";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String courseText = Request.Get(url2).execute().returnContent().asString();


        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course course = mapper.fromJson(courseText, Course.class);
        System.out.println(course.toString() + "\n");
        System.out.println("opiskelijanumero: " + studentNr + "\n");
        
        int totalHours = 0;
        int totalCompleted = 0;
        for (Submission submission : subs) {
            String week = "week" + submission.getWeek();
            submission.setMax(course.getMaxForWeek(week));
            totalHours += submission.getHours();
            totalCompleted += submission.completed();
            System.out.println(submission);
        }
        System.out.println("\nYhteensä: " + totalCompleted + " tehtävää " + totalHours + "tuntia");

    }
}
