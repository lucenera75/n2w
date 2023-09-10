package co.nz.rdf.n2w;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class NumberConverterTest {

    @Test
    public void testZero() {
        assertEquals("ZERO DOLLARS", N2wController.convertIntegerPart(0));
    }

    @Test
    public void testSingleDigit() {
        assertEquals("ONE DOLLAR", N2wController.convertIntegerPart(1));
    }

    @Test
    public void testTwoDigits() {
        assertEquals("TWELVE DOLLARS", N2wController.convertIntegerPart(12));
    }

    @Test
    public void testThreeDigits() {
        assertEquals("ONE HUNDRED TWENTY THREE DOLLARS", N2wController.convertIntegerPart(123));
    }

    @Test
    public void testThousands() {
        assertEquals("ONE THOUSAND TWO HUNDRED THIRTY FOUR DOLLARS", N2wController.convertIntegerPart(1234));
    }

    @Test
    public void testMillions() {
        assertEquals("ONE MILLION TWO HUNDRED THIRTY FOUR THOUSAND FIVE HUNDRED SIXTY SEVEN DOLLARS", N2wController.convertIntegerPart(1234567));
    }

    @Test
    public void testBillions() {
        assertEquals("ONE BILLION TWO HUNDRED THIRTY FOUR MILLION FIVE HUNDRED SIXTY SEVEN THOUSAND EIGHT HUNDRED NINETY DOLLARS", N2wController.convertIntegerPart(1234567890));
    }

}
