import java.math.BigDecimal;

public class VisitorRateCalculator implements ICalculateParkingRate {
    private final BigDecimal FREE_AMOUNT = new BigDecimal("10");

    @Override
    public BigDecimal calculateRate(BigDecimal calculatedRate) {
        // Check if the calculated rate is less than or equal to the free amount and return a zero charge.
        if (calculatedRate.compareTo(FREE_AMOUNT) <= 0)
            return BigDecimal.ZERO;

        return calculatedRate;
    }
}
