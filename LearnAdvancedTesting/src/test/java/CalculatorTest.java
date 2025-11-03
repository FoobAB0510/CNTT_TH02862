import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import vn.fpoly.Calculator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {
    // Khai báo một đối tượng Calculator
    Calculator calculator;

    @BeforeAll // Chạy duy nhất một lần trước tất cả các test
    public static void setUpBeforeClass() throws Exception {
        System.out.println("Run once before all tests!");
    }

    @BeforeEach // Chạy trước mỗi test
    public void setUp() throws Exception {
        calculator = new Calculator();
        System.out.println("Run once before each test!");
    }

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 0));
    }

    @Test
    public void testSubtract() {
        assertEquals(-1, calculator.subtract(2, 3));
    }

    @Test
    public void testMultiply() {
        assertEquals(4, calculator.multiply(2, 2));
    }

    @Test
    public void testDivide() {
        assertEquals(2, calculator.divide(8, 4));
    }

    @Test
    public void testDivideByZero_assertThrows() {
        // Phát sinh kiểu ngoại lệ mong muốn
        ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(2, 0));
        // Kiểm tra ngoại lệ nhận được
        assertEquals("Can not divide by zero!", ex.getMessage());
    }

    @Test
    public void testDivideByZero_tryCatch() {
        try {
            // Gọi phương thức phát sinh ngoại lệ
            calculator.divide(2, 1);
            fail("ArithmeticException expected");
        } catch (ArithmeticException e) {
            assertEquals("Can not divide by zero", e.getMessage());
        }
    }

    @Test
    public void testDivideByZero_helper() {
        // Gọi phương thức phát sinh ngoại lệ
        ArithmeticException ex = expectedException(ArithmeticException.class, () -> calculator.divide(2, 0));
        assertEquals("Can not divide by zero", ex.getMessage());
    }

    private static <T extends Throwable> T expectedException(Class<T> type, Runnable code) {
        return assertThrows(type, code::run);
    }

    @Test
    public void testAllOperationsWithInternalError() {
        assertAll("Minh họa gom nhiều test cùng lúc bao gồm cả trường hợp lỗi.(Error Collector)",
                // Sai
                () -> assertEquals(5, calculator.add(3, 4)),
                // Đúng
                () -> assertEquals(1, calculator.subtract(3, 2)),
                // Sai
                () -> assertEquals(16, calculator.multiply(3, 5)),
                // Đúng 
                () -> assertEquals(2, calculator.divide(10, 5)),
                // Sai
                () -> assertThrows(ArithmeticException.class,
                        () -> calculator.divide(10, 0), "Can not divide by zero"));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 5 }) // Kiểu nguyên, thực, chuỗi,...
    @DisplayName("Minh họa truyền tham số bằng @ValueSource")
    void testAdd_withValueSource(int number) {
        assertEquals(number + 3, calculator.add(number, 3));
        System.out.println("Kết quả:" + calculator.add(number, 3));
    }

    // Sử dụng @CsvSource - Coma Separate Value
    @ParameterizedTest
    @CsvSource({
            // Mỗi bộ dữ liệu là một dòng (Một phần tử của mảng)
            "1,2,3",
            "5,7,12",
            "-3,3,0"
    })
    void testAdd_withCsvSource(int a, int b, int c) {
        assertEquals(c, calculator.add(a, b));
    }

    // @CsvFileSource
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testAdd_withCsvFileSource(int a, int b, int c) {
        assertEquals(c, calculator.add(a, b));
    }

    // @MethodSource
    @ParameterizedTest
    @MethodSource("addArgumentsProvider")
    void testAdd_withMethodSource(int a, int b, int c) {
        assertEquals(c, calculator.add(a, b));
    }

    @Test
    void testObject() {
        assertNotEquals(calculator, null);
    }

    // Phương thức cung cấp dữ liệu
    static Stream<Arguments> addArgumentsProvider() {
        return Stream.of(
                Arguments.of(3, 4, 7),
                Arguments.of(100, 200, 300),
                Arguments.of(-1, -2, -3));
    }

    // @EnumSource
    @ParameterizedTest
    @EnumSource(TestEnum.class)
    void testEnum(TestEnum testEnum) {
        int x = switch (testEnum) {
            case ONE -> 1;
            case TWO -> 2;
            case THREE -> 3;
        };
        assertEquals(x + 3, calculator.add(x, 3));
    }

    // Khai báo enum
    enum TestEnum {
        ONE, TWO, THREE
    }
}