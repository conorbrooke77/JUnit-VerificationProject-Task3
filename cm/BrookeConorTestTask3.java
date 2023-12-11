
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BrookeConorTestTask3 {

    //Some predefined values for a few of the tests below;
    private ArrayList<Period> normalPeriods = new ArrayList<>(Arrays.asList(new Period(4, 9), new Period(14, 19)));
    private ArrayList<Period> reducedPeriods = new ArrayList<>(Arrays.asList(new Period(10, 14), new Period(19, 23)));

    // Tests for Period constructor
    
    @Test
    public void periodConstructorTestCase1() {  
        // Start is less than 0.
        assertThrows(IllegalArgumentException.class, () -> new Period(-1, 5));
    }

    @Test
    public void periodConstructorTestCase2() {
        // Start is greater than 24.      
        assertThrows(IllegalArgumentException.class, () -> new Period(25, 26));
    }

    @Test
    public void periodConstructorTestCase3() {
        // Start is at the very beginning with valid end.
        assertInstanceOf(Period.class, new Period(0, 5));
    }

    @Test
    public void periodConstructorTestCase4() {
        // Start is at the boundary end. 
        assertThrows(IllegalArgumentException.class, () -> new Period(24, 24));
    }

    @Test
    public void periodConstructorTestCase5() {
        // End is less than 0.
        assertThrows(IllegalArgumentException.class, () -> new Period(5, -1));
    }

    @Test
    public void periodConstructorTestCase6() {
        // End is greater than 24.
        assertThrows(IllegalArgumentException.class, () -> new Period(5, 25));
    }

    @Test
    public void periodConstructorTestCase7() { 
        // End is at the very beginning.
        assertThrows(IllegalArgumentException.class, () -> new Period(0, 0));
    }

    @Test
    public void periodConstructorTestCase8() {
        // End is at the boundary end. 
        assertInstanceOf(Period.class, new Period(0, 24));
    }

    @Test
    public void periodConstructorTestCase9() {
        // Start is greater than end (short duration).
        assertThrows(IllegalArgumentException.class, () -> new Period(1, 0));
    }

    @Test
    public void periodConstructorTestCase10() {
        // Start is greater than end (longer duration).
        assertThrows(IllegalArgumentException.class, () -> new Period(23, 2));
    }

    @Test
    public void periodConstructorTestCase11() {
        // Both start and end are within valid range.
        assertInstanceOf(Period.class, new Period(12, 18));
    }

    @Test
    public void periodConstructorTestCase12() {
        // Start and end are at the boundary end.
        assertInstanceOf(Period.class, new Period(23, 24));
    }

    @Test
    public void periodConstructorTestCase13() {
        // Start < 24 and end > 24.
        assertThrows(IllegalArgumentException.class, () -> new Period(25, 10));
    }

    @Test
    public void periodConstructorTestCase16() {
        // Both start and end are the same and within the valid range.
        assertThrows(IllegalArgumentException.class, () -> new Period(10, 10));
    }

    // Tests for Period duration() method


    @Test
    public void periodDurationTestCase1() {
        // Duration for the maximum possible period.
        Period p = new Period(0, 24);
        assertEquals(24, p.duration());
    }

    @Test
    public void periodDurationTestCase2() {
        // Duration when start is at the beginning.
        Period p = new Period(0, 12);
        assertEquals(12, p.duration());
    }

    @Test
    public void periodDurationTestCase3() {
        // Duration when the end is at the boundary end.
        Period p = new Period(12, 24);
        assertEquals(12, p.duration());
    }

    @Test
    public void periodDurationTestCase4() {
        // Duration is only for one hour.
        Period p = new Period(10, 11);
        assertEquals(1, p.duration());
    }


    // Tests for Period overlaps(Period period) method


    @Test
    public void periodOverlapsTestCase1() {
        // The current period is before the Parameter period.
        Period p1 = new Period(0, 5);
        Period p2 = new Period(6, 12);
        assertFalse(p1.overlaps(p2));
    }

    @Test
    public void periodOverlapsTestCase2() {
        // The current period is after the Parameter period.
        Period p1 = new Period(12, 24);
        Period p2 = new Period(0, 11);
        assertFalse(p1.overlaps(p2));
    }

    @Test
    public void periodOverlapsTestCase3() {
        // The Parameter period is completely within the Current period.
        Period p1 = new Period(0, 24);
        Period p2 = new Period(1, 23);
        assertTrue(p1.overlaps(p2));
    }

    @Test
    public void periodOverlapsTestCase4() {
        // The Parameter period starts before and ends within the Current period.
        Period p1 = new Period(5, 10);
        Period p2 = new Period(3, 9);
        assertTrue(p1.overlaps(p2));
    }

    @Test
    public void periodOverlapsTestCase5() {
        // The Parameter period starts within and ends after the Current period.
        Period p1 = new Period(12, 18);
        Period p2 = new Period(14, 20);
        assertTrue(p1.overlaps(p2));
    }

    @Test
    public void periodOverlapsTestCase6() {
        // The start of the Current period is right after the end of the Parameter period.
        Period p1 = new Period(12, 18);
        Period p2 = new Period(6, 12);
        assertFalse(p1.overlaps(p2));
    }

    @Test
    public void periodOverlapsTestCase7() {
        // The end of the Current period is right before the start of the Parameter period.
        Period p1 = new Period(0, 12);
        Period p2 = new Period(12, 24);
        assertFalse(p1.overlaps(p2));
    }

    @Test
    public void periodOverlapsTestCase8() {
        // Both the current period and the Parameter period are the same.
        Period p1 = new Period(1, 23);
        Period p2 = new Period(1, 23);
        assertTrue(p1.overlaps(p2));
    }


    // Tests for Rate constructor

    @Test
    public void rateConstructorTestCase1() {
        // Valid Kinds (STAFF, STUDENT, MANAGEMENT, VISITOR) combined with other valid parameters.
        for (CarParkKind kind : CarParkKind.values()) {
            assertInstanceOf(Rate.class, new Rate(kind, new BigDecimal(2), new BigDecimal(1), normalPeriods, reducedPeriods));
        }
    }

    @Test
    public void rateConstructorTestCase2() {
        // Invalid Kind (null)
        assertThrows(IllegalArgumentException.class, () -> new Rate(null, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods));
    }

    @Test
    public void rateConstructorTestCase3() {
        // Negative normal rate value
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("-1"), new BigDecimal("-5"), new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void rateConstructorTestCase4() {
        // Null normal rate value
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, null, BigDecimal.ZERO, new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void rateConstructorTestCase5() {
        // Both Rates have valid positive value
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STAFF, new BigDecimal("2"), new BigDecimal("1.9"), normalPeriods, reducedPeriods));
    }

    @Test
    public void rateConstructorTestCase6() {
        //Both Rates are zero
        //This test shouldn't fail based on the specification.
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STAFF, BigDecimal.ZERO, BigDecimal.ZERO, new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void rateConstructorTestCase7() {
        // Negative reduced rate value
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("-1"), new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void rateConstructorTestCase8() {
        //Null reduced rate value
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("10"), null, normalPeriods, reducedPeriods));
    }


    @Test
    public void rateConstructorTestCase9() {
        // Invalid Scenario: normalRate < reducedRate
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("1.8"), new BigDecimal("10.8"), new ArrayList<>(), new ArrayList<>()));
    }

    
    // NormalPeriods and ReducedPeriods Tests


    @Test
    public void rateConstructorTestCase10() {
        // Valid Non-overlapping Periods
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("1.8"), normalPeriods, reducedPeriods));
    }

    @Test
    public void rateConstructorTestCase11() {
        // Null normalPeriods List
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), null, reducedPeriods));
    }

    @Test
    public void rateConstructorTestCase12() {
        // Null reducedPeriods List
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, null));
    }

    @Test
    public void rateConstructorTestCase13() {
        // Equal Normal and Reduced Rates:
        assertInstanceOf(Rate.class, new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("10"), normalPeriods, reducedPeriods));
    }

    @Test
    public void rateConstructorTestCase14() {
        // Both lists are null
        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), null, null));
    }


    //Additional Branch Coverage Tests ( White Box Test Cases )


    @Test
    public void rateConstructorWhiteBoxTestCase1() {
        // Overlapping Periods (Within the normalPeriod List only)

        // This will overlap with an existing period in normalPeriods
        normalPeriods.add(new Period(3, 6));

        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("1.8"), normalPeriods, reducedPeriods));

        // Removing for other tests using normalPeriods ArrayList
        normalPeriods.remove(2);
    }

    @Test
    public void rateConstructorWhiteBoxTestCase2() {
        // Overlapping Periods (Within the reducedPeriod List only)

        // This will overlap with an existing period in reducedPeriods
        reducedPeriods.add(new Period(12, 15));

        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("1.8"), normalPeriods, reducedPeriods));

        // Removing for other tests using reducedPeriods ArrayList
        reducedPeriods.remove(2);
    }

    @Test
    public void rateConstructorWhiteBoxTestCase3() {
        // Overlapping Between Both Lists

        // This period in reducedPeriods will overlap with an existing period in normalPeriods
        reducedPeriods.add(new Period(3, 6));

        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("10"), BigDecimal.ZERO, normalPeriods, reducedPeriods));

        // Removing for other tests using normalPeriods ArrayList
        reducedPeriods.remove(2);
    }

    @Test
    public void rateConstructorWhiteBoxTestCase4() {
        //Adding element to front of normalPeriod so the loop terminates on the first iteration because isValid becomes false as overlap is at beginning of array

        // This will overlap with an existing period in normalPeriods. This period will be added to the start of the array.
        normalPeriods.add(0,new Period(11, 12));

        assertThrows(IllegalArgumentException.class, () -> new Rate(CarParkKind.STAFF, new BigDecimal("10"), BigDecimal.ZERO, normalPeriods, reducedPeriods));

        // Removing for other tests using normalPeriods ArrayList
        reducedPeriods.remove(0);
    }


    //Test for Rate Calculate method: calculate(Period periodStay)


    @Test
    public void rateCalculateTestCase1() {
        // Fully Within Normal Rate Period:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(5, 8));
        assertEquals(new BigDecimal("10"), charge);
    }

    @Test
    public void rateCalculateTestCase2() {
        // Fully Within Reduced Rate Period:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(11, 13));
        assertEquals(new BigDecimal("10"), charge);
    }

    //Modified this testcase's charge value from 40 to 10 as per the updated specification;
    @Test
    public void rateCalculateTestCase3() {
        // Overlaps Normal and Reduced Rate Periods:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(8, 15));
        assertEquals(new BigDecimal("10"), charge);
    }

    @Test
    public void rateCalculateTestCase4() {
        // Outside Any Rate Period:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(0, 4));
        assertEquals(BigDecimal.ZERO, charge);
    }

    //Modified this testcase's charge value from 40 to 10 as per the updated specification;
    @Test
    public void rateCalculateTestCase5() {
        // Starts at Rate Period Boundary:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(4, 8));
        assertEquals(new BigDecimal("10"), charge);
    }

    //Modified this testcase's charge value from 30 to 10 as per the updated specification;
    @Test
    public void rateCalculateTestCase6() {
        // Ends at Rate Period Boundary:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(8, 14));
        assertEquals(new BigDecimal("10"), charge);
    }

    //Modified this testcase's charge value from 120 to 10 as per the updated specification;
    @Test
    public void rateCalculateTestCase7() {
        // Boundary of Multiple Rate Periods:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(4, 19));
        assertEquals(new BigDecimal("10"), charge);
    }

    //Modified this testcase's charge value from 140 to 10 as per the updated specification;
    @Test
    public void rateCalculateTestCase8() {
        // Maximum Possible Duration Stay:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(0, 24));
        assertEquals(new BigDecimal("10"), charge);
    }

    @Test
    public void rateCalculateTestCase9() {
        // Adjacent to Rate Periods:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(9, 10));
        assertEquals(BigDecimal.ZERO, charge);
    }

    //Modified this testcase's charge value from 75 to 10 as per the updated specification;
    @Test
    public void rateCalculateTestCase10() {
        // Overlaps with Transition Points:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(9, 20));
        assertEquals(new BigDecimal("10"), charge);
    }

    //Modified this testcase's charge value from 4020 to 10 as per the updated specification;
    @Test
    public void rateCalculateTestCase11() {
        // Large Difference Between Rates:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("1000"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(6, 15));
        assertEquals(new BigDecimal("10"), charge);
    }

    @Test
    public void rateCalculateTestCase12() {
        // Null Period Stay:
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        assertThrows(NullPointerException.class, () -> rate.calculate(null));
    }

    @Test
    public void rateCalculateTestCase13() {
        // Normal and Reduced Period Lists are empty:
        ArrayList<Period> emptyNormalPeriods = new ArrayList<>();
        ArrayList<Period> emptyReducedPeriods = new ArrayList<>();
        Rate rate = new Rate(CarParkKind.STAFF, new BigDecimal("10"), new BigDecimal("5"), emptyNormalPeriods, emptyReducedPeriods);
        BigDecimal charge = rate.calculate(new Period(5, 8));
        assertEquals(BigDecimal.ZERO, charge);
    }

    @Test
    public void rateCalculateTestCase14() {
        //Calculating when Normal and Reduced Rates are Zero
        Rate rate = new Rate(CarParkKind.STAFF, BigDecimal.ZERO, BigDecimal.ZERO, normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(5, 8));
        assertEquals(BigDecimal.ZERO, charge);
    }

    // VISITOR Kind Test Cases

    //Assumptions made with the roundings
    //Error in code that has if calculating a Visitor, return 0 as the charge
    //Modified this testcase's charge value from 30 to 10 as per the updated specification;
    //Discount applied, the first 10.00 is free, 50% reduction above that, 20*0.5 =10
    @Test
    public void rateCalculateTestCase15() {
        // Visitor Car-park Kind total calculated cost is greater than 10.00
        Rate rate = new Rate(CarParkKind.VISITOR, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(5, 8));
        assertEquals(new BigDecimal("10.00"), charge);
    }
    @Test
    public void rateCalculateTestCase16() {
        // Visitor Car-park Kind total calculated cost is equal to 10.00, making the expected payment free.
        Rate rate = new Rate(CarParkKind.VISITOR, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(5, 6));
        assertEquals(new BigDecimal("0"), charge);
    }
    @Test
    public void rateCalculateTestCase17() {
        // Visitor Car-park Kind total calculated cost is just above the free boundary 10
        Rate rate = new Rate(CarParkKind.VISITOR, new BigDecimal("10"), new BigDecimal("5.01"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(10, 12));
        assertEquals(new BigDecimal("0.01"), charge);
    }

    @Test
    public void rateCalculateTestCase18() {
        // MANAGEMENT Car-park Kind is in a free period
        Rate rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(9, 10));
        assertEquals(new BigDecimal("5"), charge);
    }

    @Test
    public void rateCalculateTestCase19() {
        // MANAGEMENT Car-park Kind is above minimum payable
        Rate rate = new Rate(CarParkKind.MANAGEMENT, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(10, 14));
        assertEquals(new BigDecimal("20"), charge);
    }

    @Test
    public void rateCalculateTestCase20() {
        // STUDENT Car-park Kind is just above the no-reduction boundary
        Rate rate = new Rate(CarParkKind.STUDENT, new BigDecimal("10"), new BigDecimal("5.52"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(21, 22));
        assertEquals(new BigDecimal("5.51"), charge);
    }

    //Assumptions made with the roundings
    //Modified this testcase's charge value from 30 to 10 as per the updated specification;
    @Test
    public void rateCalculateTestCase21() {
        // STUDENT Car-park Kind:
        Rate rate = new Rate(CarParkKind.STUDENT, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(5, 8));
        assertEquals(new BigDecimal("21.92"), charge);
    }

    @Test
    public void rateCalculateTestCase22() {
        // STUDENT Car-park Kind is below reduction rate
        Rate rate = new Rate(CarParkKind.STUDENT, new BigDecimal("10"), new BigDecimal("5"), normalPeriods, reducedPeriods);
        BigDecimal charge = rate.calculate(new Period(10, 11));
        assertEquals(new BigDecimal("5"), charge);
    }

}

