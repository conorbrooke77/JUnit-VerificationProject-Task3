import java.math.BigDecimal;

public class ManagementRateCalculator implements ICalculateParkingRate {
    private static final BigDecimal MIN_PAYABLE = new BigDecimal("5");

    @Override
    public BigDecimal calculateRate(BigDecimal calculatedRate) {
        // Check if the calculated rate is less than or equal to the minimum payable amount
        // Otherwise, return the original calculated rate
        if (calculatedRate.compareTo(MIN_PAYABLE) <= 0) {
            // If it is, return the minimum payable amount
            return MIN_PAYABLE;
        }
        return calculatedRate;
    }
}
