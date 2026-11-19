package testNGTests;

import com.school21.MethodExamples;
import com.school21.Validator;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestsExampleTestNG {
    
    private SoftAssert softAssert;
    
    @BeforeClass
    public void beforeClass() {
        System.out.println("Сейчас автотесты будут запущены в верном порядке (TestNG)");
        softAssert = new SoftAssert();
    }
    
    @AfterClass
    public void afterClass() {
        System.out.println("Спасибо за внимание! (TestNG)");
        softAssert.assertAll();
    }
    
    @Test(priority = 1)
    public void testFactorial() {
        softAssert.assertEquals(MethodExamples.factorial(0), 1.0, 0.001);
        softAssert.assertEquals(MethodExamples.factorial(5), 120.0, 0.001);
    }
    
    @Test(priority = 2)
    public void testIsPrime() {
        softAssert.assertTrue(MethodExamples.isPrime(7));
        softAssert.assertFalse(MethodExamples.isPrime(8));
    }
    
    @Test(priority = 3)
    public void testIsPerfect() {
        softAssert.assertTrue(MethodExamples.isPerfect(6));
        softAssert.assertFalse(MethodExamples.isPerfect(10));
    }
    
    @Test(priority = 4)
    public void testDigitCountInNumber() {
        softAssert.assertEquals(MethodExamples.digitCountInNumber(112, 1), 2);
    }
    
    @Test(priority = 5, dataProvider = "personData")
    public void testValidatePersonString(String input) {
        softAssert.assertTrue(Validator.validatePersonString(input));
    }
    
    @DataProvider(name = "personData")
    public Object[][] getPersonData() {
        return new Object[][] {
            {"Alexander 25 years"},
            {"Ekaterina 30 years"},
            {"Vladimir 45 years"}
        };
    }
    
    @Test(priority = 6, dataProvider = "palindromeData")
    public void testIsPalindromeWithData(String input, boolean expected) {
        if (input == null) {
            softAssert.assertEquals(Validator.isPalindrome(null), expected);
            return;
        }
        softAssert.assertEquals(Validator.isPalindrome(input.trim()), expected);
    }
    
    @DataProvider(name = "palindromeData")
    public Object[][] getPalindromeData() {
        return new Object[][] {
            {"Madam", true},
            {"Hello", false},
            {"12321", true},
            {"", true},
            {null, false}
        };
    }
    
    @Test(priority = 7)
    public void is2shouldBeFirst() {
        System.out.println("Hi I am first test (TestNG)");
        softAssert.assertTrue(6 > 5);
    }
    
    @Test(priority = 8)
    public void is3shouldBeSecond() {
        System.out.println("Hi I am second test (TestNG)");
        softAssert.assertEquals(10, 5 + 5);
    }
    
    @Test(priority = 9)
    public void is1shouldBeThird() {
        System.out.println("Hi I am third test (TestNG)");
        softAssert.assertTrue(145 > 0);
    }
}
