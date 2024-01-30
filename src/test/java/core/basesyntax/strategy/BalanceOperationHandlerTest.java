package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.transaction.FruitTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static core.basesyntax.Operation.BALANCE;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class BalanceOperationHandlerTest {
    private OperationHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new BalanceOperationHandler();
        Storage.storage.put("apple", 10);
        Storage.storage.put("banana", 2);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Storage.storage.clear();
    }

    @Test
    void testWriteToStorageWithNullContent() {
        assertThrows(RuntimeException.class, () -> handler.handle(null));
    }

    @Test
    public void testHandleTransactionWithNullFruit() {
        FruitTransaction transaction = new FruitTransaction(BALANCE, null, 5);
        assertDoesNotThrow(() -> handler.handle(transaction));
    }

    @Test
    public void testHandleAddNewFruitToStorage() {
        FruitTransaction transaction = new FruitTransaction(BALANCE, "Lemon", 10);
        handler.handle(transaction);
        assertEquals(10, Storage.storage.get("Lemon"));
    }

    @Test
    public void testHandleUpdateExistingFruitQuantityWithNegativeValue() {
        FruitTransaction transaction = new FruitTransaction(BALANCE, "banana", -2);
        handler.handle(transaction);
        assertEquals(-2, Storage.storage.get("banana"));
    }
}