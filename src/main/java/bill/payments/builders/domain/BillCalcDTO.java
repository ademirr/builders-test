package bill.payments.builders.domain;

import java.math.BigDecimal;

public record BillCalcDTO(String original_amount, BigDecimal amount, String due_date, String payment_date, BigDecimal interest_amount_calculated, BigDecimal fine_amount_calculated) {

}
