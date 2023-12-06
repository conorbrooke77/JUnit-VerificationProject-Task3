import java.math.BigDecimal;

public class StudentRateCalculator implements ICalculateParkingRate {
    private final BigDecimal LIMIT_BEFORE_REDUCTION = new BigDecimal("5.5");
    private final BigDecimal REDUCTION_RATE = new BigDecimal("0.33"); // 33%

    @Override
    public BigDecimal calculateRate(BigDecimal calculatedRate) {
        // Check if the calculated rate exceeds the Limit
        if (calculatedRate.compareTo(LIMIT_BEFORE_REDUCTION) > 0) {
            // Calculate the amount after the limit
            BigDecimal aboveLimit = calculatedRate.subtract(LIMIT_BEFORE_REDUCTION);

            // Apply a 33% reduction
            BigDecimal reducedAmount = aboveLimit.multiply(REDUCTION_RATE);

            // Subtract the reduced amount from the excess amount and add the limit
            return calculatedRate.subtract(reducedAmount);
        } else {
            // If the calculated rate is below the limit, return it as is
            return calculatedRate;
        }
    }
}
