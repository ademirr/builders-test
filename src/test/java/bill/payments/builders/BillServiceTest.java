package bill.payments.builders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import bill.payments.builders.domain.Bill;
import bill.payments.builders.domain.BillCalc;
import bill.payments.builders.domain.BillPaymentDTO;
import bill.payments.builders.service.BillService;

public class BillServiceTest {
	
	@Test
	public void totalBillWithXYZBillTypeTest() {
		BillCalc billCalc = new BillService().billCalc(new Bill("", "2022-03-03", "100.00", "", "", "XYZ"), new BillPaymentDTO("", "2023-04-30"));
		assertEquals(billCalc.getTotal(), new BigDecimal("100.00"));
	}

}
