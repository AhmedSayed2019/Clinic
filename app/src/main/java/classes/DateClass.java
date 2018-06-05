package classes;

public class DateClass {
    String day;
    String start;
    String end;
    int dayId;

    public DateClass(String day, String start, String end, int dayId) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.dayId = dayId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }
}
