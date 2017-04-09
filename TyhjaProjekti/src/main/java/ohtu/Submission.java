package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.fluent.Request;

public class Submission {

    private String student_number;
    private int week;
    private boolean a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21;
    private int hours;
    private String done;
    private int max;

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getWeek() {
        return week;
    }
    
    public int getHours(){
        return hours;
    }

    public int completed() {
        done = "";
        int result = 0;
        Boolean[] tasks = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21};
        for (int i = 0; i < 21; i++) {
            if (tasks[i]) {
                result++;
                done += " " + (i + 1);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "viikko " + week + ": tehtyjä tehtäviä yhteensä: " + completed() + " (maksimi "+ max + "), aikaa kului " + hours + " tuntia, "
                + "tehdyt tehtävät:" + done;
    }

}
