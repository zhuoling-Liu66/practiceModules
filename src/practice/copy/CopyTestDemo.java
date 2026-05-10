package practice.copy;

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
    }

    private void deepCopyShouldCreateIndependentAddressObject() {
        Person original = new Person("Bob", 22, new Address("Hangzhou"));
        Person copy = original.deepCopy();

        copy.getAddress().setName("Shenzhen");

        assertNotSame(original.getAddress(), copy.getAddress(), "Deep copy should create a different Address object");
        assertEquals("Hangzhou", original.getAddress().getName(), "Changing the copy should not affect the original address");
        assertEquals("Shenzhen", copy.getAddress().getName(), "The copied address should keep its own updated value");
    }

    private void deepCopyShouldKeepOriginalFieldValuesAtCopyTime() {
        Person original = new Person("Carol", 25, new Address("Nanjing"));
        Person copy = original.deepCopy();

        assertNotSame(original, copy, "Deep copy should create a new Person instance");
        assertEquals(original.getName(), copy.getName(), "Copied name should match the original value");
        assertEquals(original.getAge(), copy.getAge(), "Copied age should match the original value");
        assertEquals(original.getAddress().getName(), copy.getAddress().getName(), "Copied address content should match at copy time");
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

    private static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            throw new AssertionError(message + ", expected=" + expected + ", actual=" + actual);
        }
    }

    private static void assertSame(Object expected, Object actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message);
        }
    }

    private static void assertNotSame(Object first, Object second, String message) {
        if (first == second) {
            throw new AssertionError(message);
        }
    }
}
