package bank.account.dto;

import lombok.*;

import java.util.function.Function;

@Data
@Builder
public class DepositAccountDto implements AccountDto {
    /**
     * Функция для подсчета процента Депозитного аккаунта
     */
    private Function<Double, Double> depositPercentFunc;
}
