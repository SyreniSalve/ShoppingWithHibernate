package shopping.test;

import java.util.Arrays;

public class EntityTestRunner {

    public static void runTests(EntityTest...tests){
        Arrays.stream(tests)
                .forEach(EntityTestRunner::runTest);
    }

    private static void runTest(EntityTest test) {
        System.out.printf("%s %s %s%n ", EntityTest.PREFIX, test.getClass().getSimpleName(), EntityTest.SUFFIX);
        test.run();
    }
}
