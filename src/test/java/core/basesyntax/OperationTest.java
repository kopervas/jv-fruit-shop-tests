package core.basesyntax;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    @Test
    void getOperationFromCodeWithNullValue() {
        assertThrows(RuntimeException.class, () -> Operation.getOperationFromCode(null));
    }
}