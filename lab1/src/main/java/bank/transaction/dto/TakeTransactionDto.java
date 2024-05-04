package bank.transaction.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TakeTransactionDto implements TransactionDto {
    private Integer accId;
    private Double value;
}
