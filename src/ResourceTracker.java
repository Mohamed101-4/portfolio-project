import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ResourceTracker {
    // this stores each resource ad jow much of it i have
    private final Map<String, Integer> resources;
    // this is the number when i consider a resource too low
    private static final int CRITICAL_LEVEL = 10;

    public ResourceTracker() {
        this.resources = new HashMap<>();
    }

    public final void add(String resource, int amount) {
        this.resources.put(resource,
                this.resources.getOrDefault(resource, 0) + amount);
    }

    public final int use(String resource, int amount) {
        int currentAmount = this.resources.getOrDefault(resource, 0);
        int usedAmount = Math.min(amount, currentAmount);
        this.resources.put(resource, currentAmount - usedAmount);
        return usedAmount;
    }

    // this checks how much of a specific resource i have
    public final int quantity(String resource) {
        return this.resources.getOrDefault(resource, 0);
    }

    // what this does is that it checks if the resorce is at critical level or if its low
    public final boolean isLow(String resource) {
        return this.quantity(resource) <= CRITICAL_LEVEL;
    }

    // this is the list of all the reaources that are running low
    public final Set<String> resourcesNeeded() {
        Set<String> lowResources = new HashSet<>();
        for (String res : this.resources.keySet()) {
            if (this.isLow(res)) {
                lowResources.add(res);
            }
        }
        return lowResources;
    }

    public final void transferFrom(ResourceTrackerSecondary blank) {
        for (Map.Entry<String, Integer> entry : blank.resources.entrySet()) {
            this.add(entry.getKey(), entry.getValue());
        }
        Object resourceTrackerSecondary;
        resourceTrackerSecondary.resources.clear();
    }

    public static void main(String[] args) {

        // this makes a new tracker and adds rsources
        ResourceTracker tracker1 = new ResourceTracker();
        tracker1.add("Water", 15);
        tracker1.add("Food", 5);
        tracker1.add("Medicine", 2);

        // this shows how much of the rsource i have
        System.out.println("Water Quantity: " + tracker1.quantity("Water")); // 15
        System.out.println("Food Quantity: " + tracker1.quantity("Food")); // 5
        System.out
                .println("Medicine Quantity: " + tracker1.quantity("Medicine")); // 2

        // this uses some water
        System.out.println("Using 7 Water: " + tracker1.use("Water", 7)); // 7
        System.out.println("Water left: " + tracker1.quantity("Water")); // 8

        // this checks when the food is low
        System.out.println("Is Food low? " + tracker1.isLow("Food")); // true
        System.out.println("Low Resources: " + tracker1.resourcesNeeded()); // [Food, Medicine, Water]

        // this makes a another tracker different than the first one
        ResourceTracker tracker2 = new ResourceTracker();
        tracker2.add("Water", 10);
        tracker2.add("Food", 20);

        tracker1.transferFrom(tracker2);

        System.out.println("After transfer, tracker1 Water: "
                + tracker1.quantity("Water")); // 18
        System.out.println("tracker2 Water: " + tracker2.quantity("Water")); // 0
    }

    public final ResourceTracker newInstance() {

        throw new UnsupportedOperationException(
                "Unimplemented method 'newInstance'");
    }

    public final void transferFrom(ResourceTracker blank) {

        throw new UnsupportedOperationException(
                "Unimplemented method 'transferFrom'");
    }

}