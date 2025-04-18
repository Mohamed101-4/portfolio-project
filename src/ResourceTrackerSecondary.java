import java.util.HashSet;
import java.util.Set;

public abstract class ResourceTrackerSecondary implements ResourceTracker {

    // Level where a resource is considered critically low
    private static final int CRITICAL_LEVEL = 10;

    // Secondary method layered on quantity
    @Override
    public final boolean isLow(String resource) {
        assert resource != null : "resource must not be null";
        return this.quantity(resource) <= CRITICAL_LEVEL;
    }

    protected abstract int quantity(String resource);

    // Secondary method to list all low resources
    @Override
    public final Set<String> resourcesNeeded() {
        Set<String> needed = new HashSet<>();

        ResourceTracker copy = this.newInstance();
        copy.transferFrom(this);

        for (String res : this.getAllResources(copy)) {
            if (copy.quantity(res) <= CRITICAL_LEVEL) {
                needed.add(res);
            }
        }

        this.transferFrom(copy);
        return needed;
    }

    protected abstract ResourceTracker newInstance();

    // `toString` is a common Object method layered with kernel methods
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

    protected abstract void transferFrom(ResourceTracker copy);

    // Object method using kernel-based comparison
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

        this.transferFrom(copyThis);
        other.transferFrom(copyOther);

        return sameKeys && sameValues;
    }

    /**
     * Helper method to guess all tracked resources. Can be adjusted later.
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
