package time;

public class TimePoint implements Comparable<TimePoint>{
    private float amount;
    private TimeUnit timeUnit;
    public TimePoint(float amount, TimeUnit timeUnit) {
        this.amount = amount;
        this.timeUnit = timeUnit;
    }
    @Override
    public int compareTo(TimePoint arg0) {
        return Float.compare(this.convert(TimeUnit.SECOND).getAmount(), arg0.convert(TimeUnit.SECOND).getAmount());
    }
    public float getAmount(){
        return amount;
    }
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
    @Override
    public boolean equals(Object obj) {
        return (int)TimeUnit.SECOND.between(this, (TimePoint) obj) == 0;
    }
    public TimePoint convert(TimeUnit timeUnit) {
        Float newAmount = this.amount * this.getTimeUnit().getValueOfSeconds() / timeUnit.getValueOfSeconds();
        return new TimePoint(newAmount, timeUnit);
    }
    public TimePoint with(TimePointAdjuster adjuster) {
        return adjuster.adjust(this);
    }

}
