package cz.messe.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

@Component
public class Utils {
    
    public static int ROUNDING_SCALE = 2;

    /**
     * Return the amount reduced by the discount. Returned value is rounded.
     * 
     * @param amount
     *            origin amount
     * @param discount
     *            discount coefficient, for example 0.15 -> 15% discount
     * 
     * @return reduced amount
     */
    public BigDecimal getAmountDiscounted(BigDecimal amount, double discount) {
        return amount.subtract(amount.multiply(new BigDecimal(discount)))
                .setScale(ROUNDING_SCALE, RoundingMode.HALF_UP);
    }
    
    /**
     * Return the tax of the entered amount. Returned value is rounded.
     * 
     * @param amount
     *            entered amount
     * 
     * @return tax amount
     */
    public BigDecimal getTaxAmount(BigDecimal amount, double taxCoefficient) {
        return amount.multiply(new BigDecimal(taxCoefficient)).subtract(amount)
                .setScale(ROUNDING_SCALE, RoundingMode.HALF_UP);
    }
    
}
