import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ResourceTracker1L extends ResourceTrackerSecondary {

    // Stores each resource name and how much we have of it
    private Map<String, Integer> resources;

    // Constructor: starts with an empty tracker
    public ResourceTracker1L() {
        this.resources = new HashMap<>();
    }

    // Add more of a specific resource
    @Override
    public void add(String resource, int amount) {
        assert resource != null;
        assert amount >= 0;

        int current = this.resources.getOrDefault(resource, 0);
        this.resources.put(resource, current + amount);
    }

    // Use a certain amount of a resource, returns how much was actually used
    @Override
    public int use(String resource, int amount) {
        assert resource != null;
        assert amount >= 0;

        int current = this.resources.getOrDefault(resource, 0);
        int used = Math.min(amount, current);
        this.resources.put(resource, current - used);
        return used;
    }

    // Check how much of a resource we currently have
    @Override
    public int quantity(String resource) {
        assert resource != null;
        return this.resources.getOrDefault(resource, 0);
    }

    // Clear all resources
    @Override
    public void clear() {
        this.resources.clear();
    }

    // Create a brand new empty tracker
    @Override
    public ResourceTracker1L newInstance() {
        return new ResourceTracker1L();
    }

    // Copy resources from another tracker, and reset the other one
    @Override
    public void transferFrom(ResourceTracker source) {
        assert source != null;
        assert source != this;

        this.clear();
        for (String res : this.getAllResources(source)) {
            this.add(res, source.quantity(res));
        }

        // Reset the source tracker
        ResourceTracker blank = source.newInstance();
        source.transferFrom(blank);
    }

    // Helper: guesses which resource names are being tracked
    private Set<String> getAllResources(ResourceTracker tracker) {
        Set<String> guessed = new HashSet<>();
        String[] common = { "Water", "Food", "Medicine", "Gas", "Clothes" };

        for (String r : common) {
            if (tracker.quantity(r) > 0) {
                guessed.add(r);
            }
        }

        return guessed;
    }
}
