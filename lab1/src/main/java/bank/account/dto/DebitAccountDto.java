package bank.account.dto;

import lombok.*;

@Data
@Builder
public class DebitAccountDto implements AccountDto {
    /**
     * Процент для начисления процента дебетового аккаунта
     */
    private Double percent;
}
