
/**
 * Kernel interface for the ResourceTracker component. Provides basic
 * functionality for tracking resources.
 *
 * @author
 */
public interface ResourceTrackerKernel extends Standard<ResourceTracker> {

    /**
     * Adds the given amount to the specified resource.
     *
     * @param resource
     *            the name of the resource to add to
     * @param amount
     *            the amount of the resource to add
     * @updates this
     * @requires resource != null and amount >= 0
     * @ensures this = #this with amount added to resource
     */
    void add(String resource, int amount);

    /**
     * Uses the given amount of the specified resource. If there is not enough,
     * uses what is available.
     *
     * @param resource
     *            the name of the resource to use
     * @param amount
     *            the amount to use
     * @return the actual amount used (less than or equal to requested amount)
     * @updates this
     * @requires resource != null and amount >= 0
     * @ensures this = #this with up to amount removed from resource
     */
    int use(String resource, int amount);

    /**
     * Returns the quantity available for the specified resource.
     *
     * @param resource
     *            the resource to query
     * @return the current quantity of the resource
     * @requires resource != null
     * @ensures quantity = amount of resource in this
     */
    int quantity(String resource);

}
