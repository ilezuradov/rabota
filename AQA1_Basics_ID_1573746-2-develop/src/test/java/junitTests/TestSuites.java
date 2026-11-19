package junitTests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.jupiter.api.DisplayName;

public class TestSuites {

    @Suite
    @SelectClasses({TestsExampleJunit_MANUAL.class, TestsExampleJunit_AI.class})
    @DisplayName("Набор тестов 1 - TestsExampleJunit (manual + AI)")
    public static class Suite1 {
    }

    @Suite
    @SelectClasses({
            MethodExamplesTest_MANUAL.class,
            MethodExamplesTest_AI.class,
            ParameterizedTests_MANUAL.class,
            ParameterizedTests_AI.class,
            TestsExampleJunit_MANUAL.class,
            TestsExampleJunit_AI.class
    })
    @DisplayName("Набор тестов 2 - все тесты (manual + AI)")
    public static class Suite2 {
    }
}