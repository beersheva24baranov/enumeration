package time;

public enum TimeUnit {
SECOND(1), MINUTE(60), HOUR(3600);
private int valueOfSeconds;
TimeUnit(int valuOfSeconds) {
    this.valueOfSeconds = valuOfSeconds;
}
public int getValueOfSeconds(){
    return valueOfSeconds;
}
public float between(TimePoint point1, TimePoint point2) {
    return (point2.getAmount() * point2.getTimeUnit().getValueOfSeconds()
                - point1.getAmount() * point1.getTimeUnit().getValueOfSeconds()) / this.valueOfSeconds;
    }
}
