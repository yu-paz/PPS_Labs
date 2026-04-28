package bankaccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Starts each test with a fresh account of 100.0
        account = new BankAccount(100.0);
    }

    /** 1. @AfterEach to delete the current bank account so it can be garbage collected */
    @AfterEach
    void tearDown() {
        account = null;
    }

    @Test
    void testDeposit() {
        // 2. Deposit $50 and check that the balance is 150
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.0001);
    }

    @Test
    void testWithdraw() {
        // 3. Withdraw $40 and check that the balance is $60 (fresh account starts at 100)
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), 0.0001);
    }

    @Test
    void testInvalidDeposit() {
        // 4. Depositing a negative amount should throw an exception
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-10.0));
        // Zero should also throw (the class rejects amount <= 0)
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.0));
    }

    @Test
    void testOverdraft() {
        // 5. Withdrawing more than the current balance should throw an exception
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(150.0));
    }

    @Test
    void testNegativeInitialBalance() {
        // 6. Creating a new BankAccount with a negative initial balance should throw
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1.0));
    }

    // ---------- Part 1c: Tests for the transfer method ----------

    @Test
    void testTransferMovesMoneyBetweenAccounts() {
        BankAccount destination = new BankAccount(50.0);
        account.transfer(destination, 30.0);

        // Source went from 100 -> 70, destination went from 50 -> 80
        assertEquals(70.0, account.getBalance(), 0.0001);
        assertEquals(80.0, destination.getBalance(), 0.0001);
    }

    @Test
    void testTransferInsufficientFundsThrows() {
        BankAccount destination = new BankAccount(0.0);
        // 100 in source, trying to send 200 should throw
        assertThrows(IllegalArgumentException.class,
                     () -> account.transfer(destination, 200.0));
        // And no money should have moved
        assertEquals(100.0, account.getBalance(), 0.0001);
        assertEquals(0.0, destination.getBalance(), 0.0001);
    }

    @Test
    void testTransferNegativeAmountThrows() {
        BankAccount destination = new BankAccount(50.0);
        assertThrows(IllegalArgumentException.class,
                     () -> account.transfer(destination, -5.0));
    }

    @Test
    void testTransferToNullThrows() {
        assertThrows(IllegalArgumentException.class,
                     () -> account.transfer(null, 10.0));
    }
}
