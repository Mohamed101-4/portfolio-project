package ResourceTracker;

public class ResourceTracker1LTest {

    @Test
    public void testAddNewResource() {
        ResourceTrackerTest tracker = new ResourceTrackerTest();
        tracker.add("Water", 10);
        this.assertEquals(10, tracker.quantity("Water"));
    }

    private void assertEquals(int i, Object quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Unimplemented method 'assertEquals'");
    }

    @Test
    public void testAddExistingResource() {
        ResourceTracker1LTest tracker = new ResourceTracker1LTest();
        tracker.add("Water", 5);
        tracker.add("Water", 7);
        this.assertEquals(12, tracker.quantity("Water"));
    }

    private Object quantity(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Unimplemented method 'quantity'");
    }

    @Test
    public void testUseWithinAvailable() {
        ResourceTrackerTest tracker = new ResourceTrackerTest();
        tracker.add("Food", 10);
        int used = tracker.use("Food", 4);
        this.assertEquals(4, used);
        this.assertEquals(6, tracker.quantity("Food"));
    }

    @Test
    public void testUseMoreThanAvailable() {
        ResourceTracker1LTest tracker = new ResourceTracker1LTest();
        tracker.add("Gas", 3);
        int used = tracker.use("Gas", 5);
        this.assertEquals(3, used);
        this.assertEquals(0, tracker.quantity("Gas"));
    }

    private void add(String string, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    private int use(String string, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'use'");
    }

    @Test
    public void testClear() {
        ResourceTracker1LTest tracker = new ResourceTracker1LTest();
        tracker.add("Water", 8);
        tracker.testClear();
        this.assertEquals(0, tracker.quantity("Water"));
    }

    @Test
    public void testTransferFrom() {
        ResourceTrackerTest t1 = new ResourceTrackerTest();
        ResourceTrackerTest t2 = new ResourceTrackerTest();

        t2.add("Medicine", 5);
        t2.add("Water", 7);

        t1.transferFrom(t2);

        this.assertEquals(5, t1.quantity("Medicine"));
        this.assertEquals(7, t1.quantity("Water"));
        this.assertEquals(0, t2.quantity("Medicine"));
        this.assertEquals(0, t2.quantity("Water"));
    }

    @Test
    public void testNewInstance() {
        ResourceTrackerTest tracker = new ResourceTrackerTest();
        ResourceTracker1LTest fresh = tracker.newInstance();
        this.assertNotNull(fresh);
        this.assertEquals(0, fresh.quantity("Food"));
    }

    private void assertNotNull(ResourceTracker1LTest fresh) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Unimplemented method 'assertNotNull'");
    }
}
