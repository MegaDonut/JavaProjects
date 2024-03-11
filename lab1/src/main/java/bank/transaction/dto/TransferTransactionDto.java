package bank.transaction.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransferTransactionDto implements TransactionDto {
    private Integer fromAccId;
    private Integer fromBankId;
    private Integer toAccId;
    private Integer toBankId;
    private Double value;
}
