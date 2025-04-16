import java.util.HashSet;
import java.util.Set;

/**
 * This abstract class adds extra features to ResourceTracker by using only the
 * public methods from the kernel.
 *
 */
public abstract class ResourceTrackerSecondary implements ResourceTracker {
    // this is the amount where the resource is considered low
    private static final int CRITICAL_LEVEL = 10;

    @Override
    public final boolean isLow(String resource) {
        assert resource != null : "resource must not be null";
        return this.quantity(resource) <= CRITICAL_LEVEL;
    }

    protected abstract int quantity(String resource);

    @Override
    public final Set<String> resourcesNeeded() {
        Set<String> needed = new HashSet<>();
        // Create a copy of `this` to iterate without modifying the original
        ResourceTracker copy = this.newInstance();
        copy.transferFrom(this);

        // Check each resource in the copy to see if it's low
        for (String res : this.getAllResources(copy)) {
            if (copy.quantity(res) <= CRITICAL_LEVEL) {
                needed.add(res);
            }
        }

        // Move everything back to original tracker
        this.transferFrom(copy);

        return needed;
    }

    protected abstract ResourceTracker newInstance();

    @Override
    public final void transferFrom(ResourceTracker other) {
        assert other != null : "other must not be null";
        assert other != this : "cannot transfer from self";

        for (String res : this.getAllResources(other)) {
            this.add(res, other.quantity(res));
        }
        // Clear the other tracker
        ResourceTracker blank = other.newInstance();
        other.transferFrom(blank);
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        ResourceTracker copy = this.newInstance();
        copy.transferFrom(this);

        Set<String> resources = this.getAllResources(copy);
        int count = 0;

        for (String res : resources) {
            sb.append(res).append(": ").append(copy.quantity(res));
            count++;
            if (count < resources.size()) {
                sb.append(", ");
            }
        }

        this.transferFrom(copy);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public final boolean equals(Object obj) {
        if (!(obj instanceof ResourceTracker)) {
            return false;
        }

        ResourceTracker other = (ResourceTracker) obj;

        ResourceTracker copyThis = this.newInstance();
        copyThis.transferFrom(this);

        ResourceTracker copyOther = other.newInstance();
        copyOther.transferFrom(other);

        Set<String> thisResources = this.getAllResources(copyThis);
        Set<String> otherResources = this.getAllResources(copyOther);

        boolean sameKeys = thisResources.equals(otherResources);
        boolean sameValues = true;

        for (String res : thisResources) {
            if (copyThis.quantity(res) != copyOther.quantity(res)) {
                sameValues = false;
                break;
            }
        }

        // Restore originals
        this.transferFrom(copyThis);
        other.transferFrom(copyOther);

        return sameKeys && sameValues;
    }

    /**
     * Helper method to get all resource names from a tracker.
     *
     * @param tracker
     *            the tracker to read from
     * @return a set of resource names
     */
    private Set<String> getAllResources(ResourceTracker tracker) {

        Set<String> guessedResources = new HashSet<>();
        String[] common = { "Water", "Food", "Medicine", "Gas", "Clothes" };
        for (String r : common) {
            if (tracker.quantity(r) > 0) {
                guessedResources.add(r);
            }
        }
        return guessedResources;
    }
}
