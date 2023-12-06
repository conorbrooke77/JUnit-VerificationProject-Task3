import java.math.BigDecimal;

public class StaffRateCalculator implements ICalculateParkingRate {

    private final BigDecimal MAX_PAYABLE = new BigDecimal("10");

    @Override
    public BigDecimal calculateRate(BigDecimal calculatedRate) {
        // Check if the calculated rate exceeds the maximum payable limit
        if (calculatedRate.compareTo(MAX_PAYABLE) > 0)
            return MAX_PAYABLE;
        else
            return calculatedRate; // Otherwise, return the original calculated rate

    }
}
