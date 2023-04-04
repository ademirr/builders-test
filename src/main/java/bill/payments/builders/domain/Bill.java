package bill.payments.builders.domain;

public record Bill(String code, String due_date, String amount, String recipient_name, String recipient_document, String type) {

}
