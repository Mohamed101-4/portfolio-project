package ResourceTracker;

import java.util.Set;

public class ResourceTrackerTest {

    @Test
    public void testIsLowTrue() {
        ResourceTracker tracker = new ResourceTracker1L();
        tracker.add("Water", 5);
        this.assertTrue(tracker.isLow("Water"));
    }

    @Test
    public void testIsLowFalse() {
        ResourceTracker1L tracker = new ResourceTracker1L();
        tracker.add("Water", 20);
        assertFalse(tracker.isLow("Water"));
    }

    @Test
    public void testResourcesNeeded() {
        ResourceTracker1L tracker = new ResourceTracker1L();
        tracker.add("Water", 8);
        tracker.add("Food", 20);
        tracker.add("Medicine", 2);

        Set<String> lowResources = tracker.resourcesNeeded();

        this.assertTrue(lowResources.contains("Water"));
        this.assertTrue(lowResources.contains("Medicine"));
        this.assertTrue(lowResources.contains("Food"));
    }

    private void assertTrue(boolean contains) {
        throw new UnsupportedOperationException(
                "Unimplemented method 'assertTrue'");
    }

    @Test
    public void testToString() {
        ResourceTracker1L tracker = new ResourceTracker1L();
        tracker.add("Water", 10);
        tracker.add("Food", 15);

        String output = tracker.toString();
        this.assertTrue(output.contains("Water: 10"));
        this.assertTrue(output.contains("Food: 15"));
    }

    @Test
    public void testEqualsTrue() {
        ResourceTracker1L t1 = new ResourceTracker1L();
        ResourceTracker1L t2 = new ResourceTracker1L();

        t1.add("Water", 10);
        t2.add("Water", 10);

        this.assertTrue(t1.equals(t2));
    }

    @Test
    public void testEqualsFalseDifferentValues() {
        ResourceTracker1L t1 = new ResourceTracker1L();
        ResourceTracker1L t2 = new ResourceTracker1L();

        t1.add("Water", 10);
        t2.add("Water", 5);

        this.assertTrue(t1.equals(t2));
    }

    @Test
    public void testEqualsFalseDifferentKeys() {
        ResourceTracker1L t1 = new ResourceTracker1L();
        ResourceTracker1L t2 = new ResourceTracker1L();

        t1.add("Water", 10);
        t2.add("Food", 10);

        this.assertTrue(t1.equals(t2));
    }

    public Object quantity(String string) {
        throw new UnsupportedOperationException(
                "Unimplemented method 'quantity'");
    }

    public void add(String string, int i) {
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    public int use(String string, int i) {
        throw new UnsupportedOperationException("Unimplemented method 'use'");
    }

    public void transferFrom(ResourceTrackerTest t2) {
        throw new UnsupportedOperationException(
                "Unimplemented method 'transferFrom'");
    }

    public ResourceTracker1LTest newInstance() {
        throw new UnsupportedOperationException(
                "Unimplemented method 'newInstance'");
    }
}
