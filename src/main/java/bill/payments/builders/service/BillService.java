package bill.payments.builders.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bill.payments.builders.domain.Bill;
import bill.payments.builders.domain.BillCalc;
import bill.payments.builders.domain.BillPaymentDTO;
import bill.payments.builders.domain.Client;

@Service
public class BillService {

	private static final String BILL_API = "https://vagas.builders/api/builders/bill-payments/codes";
	private static final String TOKEN_API = "https://vagas.builders/api/builders/auth/tokens";
	private static final String SECRET = "4e8229fe-1131-439c-9846-799895a8be5b";
	private static final String ID = "bd753592-cf9b-4d1a-96b9-cb8b2c01bd12";

	public Bill apisConsumer(String barCode) {
		HttpEntity<Client> request = new HttpEntity<>(new Client(ID, SECRET, "", ""));
		RestTemplate restTemplate = new RestTemplate();
		Client client = restTemplate.postForObject(TOKEN_API, request, Client.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authorization", client.token());
	    
	    HttpEntity<Bill> request2 = new HttpEntity<>(new Bill(barCode, "", "", "", "", ""), headers);
		Bill bill = restTemplate.postForObject(BILL_API, request2, Bill.class);
		return bill;
	}
	
	public BillCalc billCalc(Bill bill, BillPaymentDTO billDTO) {
		LocalDate date = LocalDate.parse(bill.due_date());
		LocalDate date2 = LocalDate.parse(billDTO.payment_date());
		long diferencaEmDias = ChronoUnit.DAYS.between(date, date2);
		
		BigDecimal interest = null;
		BigDecimal fine = null;
		BigDecimal total = new BigDecimal(bill.amount());
		
		if (diferencaEmDias > 0 && bill.type().equals("NPC")) {
			BigDecimal amount = new BigDecimal(bill.amount());
			interest = amount.multiply(new BigDecimal(0.00033)).multiply(new BigDecimal(diferencaEmDias)).setScale(2, RoundingMode.HALF_EVEN);
			fine = amount.multiply(new BigDecimal(0.02)).setScale(2, RoundingMode.HALF_EVEN);
			total = amount.add(interest).add(fine).setScale(2, RoundingMode.HALF_EVEN);
		}
		
		return new BillCalc(billDTO.bar_code(), total, interest, fine);
	}
	
}
