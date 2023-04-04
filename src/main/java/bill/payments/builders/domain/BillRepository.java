package bill.payments.builders.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<BillCalc, Long> {

}
