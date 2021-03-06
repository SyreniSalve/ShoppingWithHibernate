package shopping.test;

import shopping.test.entity.EntityTest;

public class EntityTestRunner {

    public static void runTests(){
        EntityTestProvider.INSTANCE.getAllEntityTests().forEach(EntityTestRunner::runTest);
    }

    private static void runTest(EntityTest<?> test) {
        test.runBefore();
        try {
            test.run();
        } finally {
           test.runAfter();
        }
    }
}
