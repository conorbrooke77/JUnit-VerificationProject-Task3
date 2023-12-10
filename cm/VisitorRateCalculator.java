import java.math.BigDecimal;
import java.math.RoundingMode;

public class VisitorRateCalculator implements ICalculateParkingRate {
    private final BigDecimal FREE_AMOUNT = new BigDecimal("10");
    private final BigDecimal REDUCTION_RATE = new BigDecimal("0.50"); // 50%

    @Override
    public BigDecimal calculateRate(BigDecimal calculatedRate) {
        // Check if the calculated rate is less than or equal to the free amount and return a zero charge.
        if (calculatedRate.compareTo(FREE_AMOUNT) <= 0)
            return BigDecimal.ZERO;
        else {
            // Subtract the FREE_AMOUNT
            BigDecimal chargeableAmount = calculatedRate.subtract(FREE_AMOUNT);

            // Apply a 50% reduction to the chargeable amount
            BigDecimal reducedAmount = chargeableAmount.multiply(REDUCTION_RATE);

            // Ensure the result is rounded to 2 decimal places, suitable for currency
            return reducedAmount.setScale(2, RoundingMode.HALF_EVEN);
        }
    }
}
