package bill.payments.builders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bill.payments.builders.domain.Bill;
import bill.payments.builders.domain.BillCalc;
import bill.payments.builders.domain.BillCalcDTO;
import bill.payments.builders.domain.BillPaymentDTO;
import bill.payments.builders.domain.BillRepository;
import bill.payments.builders.service.BillService;

@RestController
@RequestMapping("bill")
public class BillPaymentController {
	
	@Autowired
	private BillRepository repository;
	
	@Autowired
	private BillService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<BillCalcDTO> billPayment(@RequestBody BillPaymentDTO billDTO) {
		Bill bill = null;
		try {
			bill = service.apisConsumer(billDTO.bar_code());
		} catch (Exception e) {
			System.out.println("Boleto " + billDTO.bar_code() + " não encontrado!");
			return ResponseEntity.notFound().build();
		}		
		
		BillCalc billCalc = null;
		try {
			billCalc = service.billCalc(bill, billDTO);
		} catch (Exception e) {
			System.out.println("Data de pagamento inválida!");
			return ResponseEntity.badRequest().build();
		}
		
		repository.save(billCalc);
		
		BillCalcDTO billCalcDTO = new BillCalcDTO(bill.amount(), billCalc.getTotal(), bill.due_date(), billDTO.payment_date(), billCalc.getInterest(), billCalc.getFine());
		
		return ResponseEntity.ok(billCalcDTO);
	}

}
