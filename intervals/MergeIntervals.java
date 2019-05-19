package algorithms.intervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    class Interval {
        int start;
        int end;
        
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        
        intervals.sort(new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        
        List<Interval> result = new ArrayList<Interval>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals) {
            if (end >= interval.start) { // overlaps
                end = Math.max(end, interval.end);
            } else { // not overlap
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        
        // add last one to result
        result.add(new Interval(start, end));
        return result;
    }
}
