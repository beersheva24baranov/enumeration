package time;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TimePointUnitTest {

    @Test
    public void testCompareTo() {
        TimePoint timePoint1 = new TimePoint(5, TimeUnit.HOUR);
        TimePoint timePoint2 = new TimePoint(7, TimeUnit.HOUR);
        assertEquals(-1, timePoint1.compareTo(timePoint2));

        TimePoint timePoint3 = new TimePoint(5, TimeUnit.HOUR);
        TimePoint timePoint4 = new TimePoint(150, TimeUnit.SECOND);
        assertEquals(1, timePoint3.compareTo(timePoint4));

        TimePoint timePoint5 = new TimePoint(5, TimeUnit.MINUTE);
        TimePoint timePoint6 = new TimePoint(20, TimeUnit.HOUR);
        assertEquals(-1, timePoint5.compareTo(timePoint6));
    
        TimePoint timePoint7 = new TimePoint(360, TimeUnit.SECOND);
        TimePoint timePoint8 = new TimePoint(6, TimeUnit.MINUTE);
        assertEquals(0, timePoint7.compareTo(timePoint8));
    }

    @Test
    public void testConvert() {
   
        TimePoint timePoint1 = new TimePoint(5, TimeUnit.HOUR);
        TimePoint timePoint1Converted = timePoint1.convert(TimeUnit.MINUTE);
        assertEquals(300, timePoint1Converted.getAmount());
        assertEquals(TimeUnit.MINUTE, timePoint1Converted.getTimeUnit());

        TimePoint timePoint3 = new TimePoint(3600, TimeUnit.SECOND);
        TimePoint timePoint3Converted = timePoint3.convert(TimeUnit.HOUR);
        assertEquals(1, timePoint3Converted.getAmount());
        assertEquals(TimeUnit.HOUR, timePoint3Converted.getTimeUnit());
    }

    @Test
    public void testEquals() {
       
        TimePoint timePoint1 = new TimePoint(100, TimeUnit.SECOND);
        assertTrue(timePoint1.equals(timePoint1));

        TimePoint timePoint2 = new TimePoint(3, TimeUnit.HOUR);
        TimePoint timePoint3 = new TimePoint(3, TimeUnit.HOUR);
        assertTrue(timePoint2.equals(timePoint3));

        TimePoint timePoint4 = new TimePoint(8, TimeUnit.MINUTE);
        TimePoint timePoint5 = new TimePoint(4, TimeUnit.SECOND);
        assertFalse(timePoint4.equals(timePoint5));

     
        TimePoint timePoint6 = new TimePoint(7, TimeUnit.SECOND);
        TimePoint timePoint7 = new TimePoint(7, TimeUnit.MINUTE);
        assertFalse(timePoint6.equals(timePoint7));

       
    }

    @Test
    public void testBetween() {
     
        TimePoint timePoint1 = new TimePoint(24, TimeUnit.HOUR);
        TimePoint timePoint2 = new TimePoint(11, TimeUnit.HOUR);
        assertEquals(-13, TimeUnit.HOUR.between(timePoint1, timePoint2));
        assertEquals(13, TimeUnit.HOUR.between(timePoint2, timePoint1));

        
        TimePoint timePoint3 = new TimePoint(1, TimeUnit.HOUR);
        TimePoint timePoint4 = new TimePoint(60, TimeUnit.MINUTE);
        assertEquals(0, TimeUnit.HOUR.between(timePoint4, timePoint3));
        assertEquals(0, TimeUnit.HOUR.between(timePoint3, timePoint4));
  
    }

    @Test
    public void testFutureProximityAdjuster() {
        // get ready
        TimePoint timePoint1 = new TimePoint(100, TimeUnit.SECOND);
        TimePoint timePoint2 = new TimePoint(20, TimeUnit.MINUTE);
        TimePoint timePoint3 = new TimePoint(21, TimeUnit.MINUTE);
        TimePoint timePoint4 = new TimePoint(29, TimeUnit.HOUR);
        TimePoint[] timePoints = {
                timePoint2, timePoint1, timePoint3, timePoint4
        };
        FutureProximityAdjuster futureProximityAdjuster = new FutureProximityAdjuster(timePoints);

      
        TimePoint timePointInFuture = new TimePoint(11, TimeUnit.SECOND);
        TimePoint findResult = futureProximityAdjuster.adjust(timePointInFuture);
        assertEquals(timePoint1, findResult);

    }
}