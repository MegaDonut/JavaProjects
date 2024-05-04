import Exceptions.TransactionException;
import Exceptions.WrongOperationException;
import bank.Bank;
import bank.CentralBank;
import bank.account.BankAccountable;
import bank.account.DebitAccount;
import bank.transaction.Transactionable;
import bank.transaction.dto.AddTransactionDto;
import bank.transaction.dto.TakeTransactionDto;
import bank.transaction.dto.TransferTransactionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import user.User;

public class SystemTest {

    @Test
    public void testNoAuthUser() {
        User user = User.builder().firstName("Vi").lastName("m").build();
        Bank bank = Bank.builder().id(1).comm(100.).limit(200.).percent(0.05).build();
        BankAccountable account = DebitAccount.builder().balance(0.).user(user).id(1).build();
        bank.addAccount(account);

        Transactionable trans = bank.createTrans(AddTransactionDto.builder().accId(1).value(300.).build());

        Assertions.assertThrows(WrongOperationException.class, trans::proceed);
    }

    @Test
    public void testAuthUser() throws TransactionException, WrongOperationException {
        User user = User.builder().firstName("Vi").lastName("m").address("ubuntu:latest").passport(1337).build();
        Bank bank = Bank.builder().id(1).comm(100.).limit(200.).percent(0.05).build();
        BankAccountable account = DebitAccount.builder().balance(0.).user(user).id(1).build();
        bank.addAccount(account);

        Transactionable trans = bank.createTrans(AddTransactionDto.builder().accId(1).value(300.).build());
        trans.proceed();
        Assertions.assertEquals(300., account.watchBalance());
    }

    @Test
    public void testAddTake() throws TransactionException, WrongOperationException {
        User user = User.builder().firstName("Vi").lastName("m").address("ubuntu:latest").passport(1337).build();
        Bank bank = Bank.builder().id(1).comm(100.).limit(200.).percent(0.05).build();
        BankAccountable account = DebitAccount.builder().balance(0.).user(user).id(1).build();
        bank.addAccount(account);

        Transactionable transAdd = bank.createTrans(AddTransactionDto.builder().accId(1).value(300.).build());
        Transactionable transTake = bank.createTrans(TakeTransactionDto.builder().accId(1).value(200.).build());

        transAdd.proceed();
        transTake.proceed();

        Assertions.assertEquals(100., account.watchBalance());
    }

    @Test
    public void testTransfer() throws TransactionException, WrongOperationException {
        User user = User.builder().firstName("Vi").lastName("m").address("ubuntu:latest").passport(1337).build();
        CentralBank cb = new CentralBank();
        Bank bankF = Bank.builder().id(1).comm(100.).limit(200.).percent(0.05).build();
        Bank bankS = Bank.builder().id(2).comm(150.).limit(250.).percent(0.1).build();
        cb.addBank(bankF);
        cb.addBank(bankS);

        BankAccountable accountF = DebitAccount.builder().balance(0.).user(user).id(1).build();
        BankAccountable accountS = DebitAccount.builder().balance(1000.).user(user).id(1).build();

        bankF.addAccount(accountF);
        bankS.addAccount(accountS);

        Transactionable transfer = cb.createTransaction(TransferTransactionDto.builder().fromAccId(1).fromBankId(2).toAccId(1).toBankId(1).value(53.).build());

        transfer.proceed();

        Assertions.assertEquals(53., accountF.watchBalance());
        Assertions.assertEquals(947., accountS.watchBalance());
    }

    @Test
    public void testUndoTransfer() throws TransactionException, WrongOperationException {
        User user = User.builder().firstName("Vi").lastName("m").address("ubuntu:latest").passport(1337).build();
        CentralBank cb = new CentralBank();
        Bank bankF = Bank.builder().id(1).comm(100.).limit(200.).percent(0.05).build();
        Bank bankS = Bank.builder().id(2).comm(150.).limit(250.).percent(0.1).build();
        cb.addBank(bankF);
        cb.addBank(bankS);

        BankAccountable accountF = DebitAccount.builder().balance(0.).user(user).id(1).build();
        BankAccountable accountS = DebitAccount.builder().balance(1000.).user(user).id(1).build();

        bankF.addAccount(accountF);
        bankS.addAccount(accountS);

        Transactionable transfer = cb.createTransaction(TransferTransactionDto.builder().fromAccId(1).fromBankId(2).toAccId(1).toBankId(1).value(53.).build());

        transfer.proceed();

        transfer.undo();

        Assertions.assertEquals(0., accountF.watchBalance());
        Assertions.assertEquals(1000., accountS.watchBalance());
    }


    @Test
    public void testUndoTrans() throws TransactionException, WrongOperationException {
        User user = User.builder().firstName("Vi").lastName("m").address("ubuntu:latest").passport(1337).build();
        Bank bank = Bank.builder().id(1).comm(100.).limit(200.).percent(0.05).build();
        BankAccountable account = DebitAccount.builder().balance(0.).user(user).id(1).build();
        bank.addAccount(account);

        Transactionable transAdd = bank.createTrans(AddTransactionDto.builder().accId(1).value(300.).build());

        transAdd.proceed();
        transAdd.undo();

        Assertions.assertEquals(0., account.watchBalance());
    }

    @Test
    public void testUndoExpiredTrans() throws TransactionException, WrongOperationException {
        User user = User.builder().firstName("Vi").lastName("m").address("ubuntu:latest").passport(1337).build();
        Bank bank = Bank.builder().id(1).comm(100.).limit(200.).percent(0.05).build();
        BankAccountable account = DebitAccount.builder().balance(0.).user(user).id(1).build();
        bank.addAccount(account);

        Transactionable transAdd = bank.createTrans(AddTransactionDto.builder().accId(1).value(300.).build());

        transAdd.proceed();
        transAdd.undo();

        Assertions.assertThrows(TransactionException.class, transAdd::undo);
    }

    @Test
    public void testSendMessages() {
        User userF = User.builder().firstName("Vi").lastName("m").address("ubuntu:latest").passport(1337).build();
        User userS = User.builder().firstName("Vi").lastName("m").address("ubuntu:latest").passport(1337).build();

        Bank bank = Bank.builder().id(1).comm(100.).limit(200.).percent(0.05).build();

        bank.subscribe(userF);
        bank.subscribe(userS);

        bank.sendMessage("Abobus");

        Assertions.assertEquals(userF.getMessages().getFirst(), userS.getMessages().getFirst());
    }
}