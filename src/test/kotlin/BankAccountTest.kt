import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertFailsWith

class BankAccountTest {
    private lateinit var debitAccount:BankAccount;
    private lateinit var creditAccount:BankAccount;
    private lateinit var checkingAccount:BankAccount
    @BeforeEach
    fun setUp() {
        debitAccount =    BankAccount(0, "debitHolder",    "debit",    1)
        creditAccount =   BankAccount(1, "creditHolder",   "credit",   1)
        checkingAccount = BankAccount(2, "checkingHolder", "checking", 1)
    }
    @Test
    fun `debit withdrawal works with positive`() {
        debitAccount.withdrawal(1);
        assertEquals(debitAccount.getBalance(), 0)
    }
    @Test
    fun `debit withdrawal fails with greater than balance`() {
        debitAccount.withdrawal(2);
        assertEquals(debitAccount.getBalance(), 1)
    }
    @Test
    fun `debit withdrawal fails with negative`() {
        assertFailsWith<IllegalArgumentException>{debitAccount.withdrawal(-1)}
    }
    @Test
    fun `debit withdrawal fails with zero`() {
        assertFailsWith<IllegalArgumentException>{debitAccount.withdrawal(0)}
    }
    @Test
    fun `credit withdrawal works with positive`(){
        creditAccount.withdrawal(1);
        assertEquals(creditAccount.getBalance(), 2)
    }
    @Test
    fun `credit withdrawal fails with negative`() {
        assertFailsWith<IllegalArgumentException>{creditAccount.withdrawal(-1)}
    }
    @Test
    fun `credit withdrawal fails with zero`() {
        assertFailsWith<IllegalArgumentException>{creditAccount.withdrawal(0)}
    }
    @Test
    fun `checking withdrawal works with larger than balance`(){
        checkingAccount.withdrawal(2);
        assertEquals(checkingAccount.getBalance(), -1)
    }
    @Test
    fun `checking withdrawal fails with negative`() {
        assertFailsWith<IllegalArgumentException>{checkingAccount.withdrawal(-1)}
    }
    @Test
    fun `checking withdrawal fails with zero`() {
        assertFailsWith<IllegalArgumentException>{checkingAccount.withdrawal(0)}
    }
    @Test
    fun `debit deposit works with positive`() {
        debitAccount.deposit(1);
        assertEquals(debitAccount.getBalance(), 2);
    }
    @Test
    fun `debit deposit fails with negative`() {
        assertFailsWith<IllegalArgumentException> {  debitAccount.deposit(-1);}
    }
    @Test
    fun `debit deposit fails with zero`() {
        assertFailsWith<IllegalArgumentException> {  debitAccount.deposit(0);}
    }
    @Test
    fun `checking deposit works with positive`() {
        checkingAccount.deposit(1);
        assertEquals(checkingAccount.getBalance(), 2);
    }
    @Test
    fun `checking deposit fails with negative`() {
        assertFailsWith<IllegalArgumentException> {  checkingAccount.deposit(-1);}
    }
    @Test
    fun `checking deposit fails with zero`() {
        assertFailsWith<IllegalArgumentException> {  checkingAccount.deposit(0);}
    }
    @Test
    fun `credit deposit works with positive`() {
        creditAccount.deposit(1);
        assertEquals(creditAccount.getBalance(), 0);
    }
    @Test
    fun `credit deposit works with less than balance`() {
        creditAccount.withdrawal(1); // balance is now 2
        creditAccount.deposit(1)
        assertEquals(creditAccount.getBalance(), 1);
    }
    @Test
    fun `credit deposit fails with greater than balance`() {
        creditAccount.deposit(2);
        assertEquals(creditAccount.getBalance(), 1);
    }
    @Test
    fun `credit deposit fails with negative`() {
        assertFailsWith<IllegalArgumentException> {  creditAccount.deposit(-1);}
    }
    @Test
    fun `credit deposit fails with zero`() {
        assertFailsWith<IllegalArgumentException> {  creditAccount.deposit(0);}
    }
    @Test
    fun getBalance() {
        assertEquals(debitAccount.getBalance(), 1)
        assertEquals(creditAccount.getBalance(), 1)
        assertEquals(checkingAccount.getBalance(), 1)
    }
    @Test
    fun getId() {
        assertEquals(debitAccount.id, 0)
        assertEquals(creditAccount.id, 1)
        assertEquals(checkingAccount.id, 2)
    }
}