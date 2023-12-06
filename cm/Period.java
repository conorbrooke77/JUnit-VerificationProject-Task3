import java.util.List;

public class Period {
     private int startHour;
     private int endHour;

    public Period(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException("start of period cannot be later or equal to end of period");
        }
        if (start < 0 || start > 24 || end > 24) {
            throw new IllegalArgumentException("start of period and end of period must be between 0 and 24");
        }
        this.startHour = start;
        this.endHour = end;
    }

    /**
     * checks if a given hour is within the period
     * @param hour the start of the hour to check
     * @return true if the hour is within the period
     */
    private Boolean isIn(int hour) {
        return hour >= this.startHour && hour < this.endHour;
    }

    //hour == 5 ||| List = Period(4, 9), Period(14, 19)
    private static Boolean isIn(int hour, List<Period> list) {
        Boolean isIn = false;
        int i = 0;
        while (i < list.size() && !isIn) {

            isIn = list.get(i).isIn(hour);
            i++;
        }
        return isIn;
    }

    /**
     * The duration of a period
     * @return the number of whole hours a this period covers
     */
    public int duration() {
        return this.endHour - this.startHour;
    }

    /**
     * Returns the numbers of hours this period is included in the list
     * @param list the list of periods to check
     * @return the number of full hours covered by this period
     */
    // Period Stay (5, 8);
    public int occurences(List<Period> list) {
        //    private ArrayList<Period> normalPeriods = new ArrayList<>(Arrays.asList(new Period(4, 9), new Period(14, 19)));
        //    private ArrayList<Period> reducedPeriods = new ArrayList<>(Arrays.asList(new Period(10, 14), new Period(19, 23)));
        int occurences = 0;
        // this.startHour == 5 || this.endHour == 8;
        for (int hour = this.startHour; hour < this.endHour; hour++) {
            //5
            if (isIn(hour, list)) {
                occurences++; //+1 //+1 //+1
            }
        }
        return occurences;
    }

    public boolean overlaps(Period period) {
        return this.endHour>period.startHour && this.startHour<period.endHour;
    }
}
