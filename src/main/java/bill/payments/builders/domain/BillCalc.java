package bill.payments.builders.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "BILL")
@Entity(name = "BillCalc")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class BillCalc {
	
	public BillCalc() {
	}
	
	public BillCalc(String code, BigDecimal total, BigDecimal interest, BigDecimal fine) {
		this.code = code;
		this.total = total;
		this.interest = interest;
		this.fine = fine;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private BigDecimal total;
    private BigDecimal interest;
    private BigDecimal fine;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getInterest() {
		return interest;
	}
	
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	
	public BigDecimal getFine() {
		return fine;
	}
	
	public void setFine(BigDecimal fine) {
		this.fine = fine;
	}

}
