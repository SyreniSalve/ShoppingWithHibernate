package shopping.test;

import shopping.test.entity.EntityTest;

import java.util.Arrays;

public class EntityTestRunner {

    public static void runTests(){
        EntityTestProvider.INSTANCE.getAllEntityTests().forEach(EntityTestRunner::runTest);
    }

    private static void runTest(EntityTest<?> test) {
        System.out.printf("%s %s %s%n ", EntityTest.PREFIX, test.getClass().getSimpleName(), EntityTest.SUFFIX);
        test.run();
    }
}
