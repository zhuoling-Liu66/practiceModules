package practice.copy;
//浅拷贝：浅拷贝会在堆上创建一个新的对象（区别于引用拷贝的一点），不过，如果原对象内部的属性是引用类型的话，
// 浅拷贝会直接复制内部对象的引用地址，也就是说拷贝对象和原对象共用同一个内部对象。
// 深拷贝：深拷贝会完全复制整个对象，包括这个对象所包含的内部对象。

public class CopyTestDemo {
    private int passed;
    private int failed;

    public static void main(String[] args) {
        new CopyTestDemo().runAllTests();
    }

    private void runAllTests() {
        run("shallowCopy_shouldShareNestedAddressReference", this::shallowCopyShouldShareNestedAddressReference);
        run("deepCopy_shouldCreateIndependentAddressObject", this::deepCopyShouldCreateIndependentAddressObject);
        run("deepCopy_shouldKeepOriginalFieldValuesAtCopyTime", this::deepCopyShouldKeepOriginalFieldValuesAtCopyTime);

        System.out.println();
        System.out.println("Test result: passed=" + passed + ", failed=" + failed);

        if (failed > 0) {
            throw new AssertionError("There are failing tests.");
        }
    }

    private void shallowCopyShouldShareNestedAddressReference() {
        Person original = new Person("Alice", 20, new Address("Shanghai"));
        Person copy = original.shallowCopy();

        copy.getAddress().setName("Beijing");

        assertSame(original.getAddress(), copy.getAddress(), "Shallow copy should keep the same Address reference");
        assertEquals("Beijing", original.getAddress().getName(), "Changing the copy should also affect the original address");
        System.out.println(original.getAddress() == copy.getAddress());

    }

    private void deepCopyShouldCreateIndependentAddressObject() {
        Person original = new Person("Bob", 22, new Address("Hangzhou"));
        Person copy = original.deepCopy();

        copy.getAddress().setName("Shenzhen");

        assertNotSame(original.getAddress(), copy.getAddress(), "Deep copy should create a different Address object");
        assertEquals("Hangzhou", original.getAddress().getName(), "Changing the copy should not affect the original address");
        assertEquals("Shenzhen", copy.getAddress().getName(), "The copied address should keep its own updated value");
        System.out.println(original.getAddress() == copy.getAddress());
    }

    private void deepCopyShouldKeepOriginalFieldValuesAtCopyTime() {
        Person original = new Person("Carol", 25, new Address("Nanjing"));
        Person copy = original.deepCopy();

        assertNotSame(original, copy, "Deep copy should create a new Person instance");
        assertEquals(original.getName(), copy.getName(), "Copied name should match the original value");
        assertEquals(original.getAge(), copy.getAge(), "Copied age should match the original value");
        assertEquals(original.getAddress().getName(), copy.getAddress().getName(), "Copied address content should match at copy time");
        System.out.println(original.getAddress() == copy.getAddress());
    }

    private void run(String testName, Runnable testCase) {
        try {
            testCase.run();
            passed++;
            System.out.println("[PASS] " + testName);
        } catch (AssertionError e) {
            failed++;
            System.out.println("[FAIL] " + testName);
            System.out.println("       " + e.getMessage());
        } catch (Exception e) {
            failed++;
            System.out.println("[ERROR] " + testName);
            System.out.println("        " + e.getMessage());
        }
    }
    /**
     * 断言两个对象【内容相等】
     * 内部使用 equals 比较内容，兼容预期值为 null 的情况
     * @param expected 期望的对象/值
     * @param actual   实际运行得到的对象/值
     * @param message  断言失败时的错误提示信息
     * @throws AssertionError 当内容不相等、或 null 匹配不一致时抛出断言错误
     */
    private static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            throw new AssertionError(message + ", expected=" + expected + ", actual=" + actual);
        }
    }

    /**
     * 断言两个对象【是同一个引用】
     * 使用 == 比较内存地址，必须为同一个对象才通过
     * @param expected 期望的对象引用
     * @param actual   实际的对象引用
     * @param message  断言失败时的错误提示信息
     * @throws AssertionError 当不是同一个对象引用时抛出断言错误
     */
    private static void assertSame(Object expected, Object actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message);
        }
    }

    /**
     * 断言两个对象【不是同一个引用】
     * 使用 == 比较内存地址，是同一个对象则失败
     * @param first  第一个对象引用
     * @param second 第二个对象引用
     * @param message 断言失败时的错误提示信息
     * @throws AssertionError 当两个是同一个对象引用时抛出断言错误
     */
    private static void assertNotSame(Object first, Object second, String message) {
        if (first == second) {
            throw new AssertionError(message);
        }
    }
}
