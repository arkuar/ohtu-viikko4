package ohtu;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arttu
 */
public class Course {

    private String name;
    private String term;
    private int week1, week2, week3, week4, week5, week6;

    public int getMaxForWeek(String week) throws NoSuchFieldException {
        Field f = this.getClass().getDeclaredField(week);
        try {
            int max = (int) f.get(this);
            return max;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + ", " + term;
    }

}
