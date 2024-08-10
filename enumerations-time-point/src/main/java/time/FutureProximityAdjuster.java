package time;
import java.util.Arrays;
public class FutureProximityAdjuster implements TimePointAdjuster{
    TimePoint [] timePoints;
    public FutureProximityAdjuster(TimePoint [] timePoints) {
            this.timePoints = Arrays.copyOf(timePoints, timePoints.length);
            Arrays.sort(this.timePoints);
        }
    
    
    @Override
    public TimePoint adjust(TimePoint timePoint) {
        int index = Arrays.binarySearch(timePoints, timePoint);
        if (index >= 0) {
            while (index < timePoints.length && timePoints[index].compareTo(timePoint) <= 0) {
                index++;
            }
        } else {
            index = -index - 1;
        }    
        return index == timePoints.length ? null : timePoints[index];
    }
}
