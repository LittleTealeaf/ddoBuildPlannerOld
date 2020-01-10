package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A representation of a craftable item in DDO, where there's multiple enchantment choices given
 * @author Tealeaf
 * @see Craftref
 */
public class Craftable {

	private String name;

	private String uuid;

	private List<Enchref> choices;

	/**
	 * Creates an empty {@link Craftable} object
	 */
	public Craftable() {
		this(UUID.randomUUID().toString());
	}

	/**
	 * Creates a {@link Craftable} object with a set UUID
	 * @param uuid UUID of the {@link Craftable}
	 */
	public Craftable(String uuid) {
		this("", uuid);
	}

	/**
	 * Creates a {@link Craftable} object with a set name and UUID
	 * @param name Name of the {@link Craftable}
	 * @param uuid UUID of the {@link Craftable}
	 */
	public Craftable(String name, String uuid) {
		this(name, uuid, new ArrayList<Enchref>());
	}

	/**
	 * Creates a {@link Craftable} object with a set name, uuid, and choices
	 * @param name Name of the {@link Craftable}
	 * @param uuid UUID of the {@link Craftable}
	 * @param choices Choices of the {@link Craftable}
	 * @see Enchref
	 */
	public Craftable(String name, String uuid, List<Enchref> choices) {
		this.name = name;
		this.uuid = uuid;
		this.choices = choices;
	}

	/**
	 * Gets the name of the {@link Craftable}
	 * @return Name of the {@link Craftable}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the {@link Craftable}
	 * @param name Name to give the {@link Craftable}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the unique UUID of the {@link Craftable}
	 * @return UUID of the {@link Craftable}
	 */
	public String getUUID() {
		return uuid;
	}

	/**
	 * Gives the {@link Craftable} a new UUID
	 * @param uuid New UUID of the {@link Craftable}
	 */
	public void setUUID(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Gives the {@link Craftable} a new random UUID
	 */
	public void newUUID() {
		this.uuid = UUID.randomUUID().toString();
	}

	/**
	 * Gets all the choices of the {@link Craftable}
	 * @return {@link Craftable} Choices
	 * @see Enchref
	 */
	public List<Enchref> getChoices() {
		return choices;
	}

	/**
	 * Gets the {@link Enchref} of the {@link Craftable} with a choice index
	 * @param index Index choice of the {@link Craftable}
	 * @return {@link Enchref} at that selection<br>Null if index is out of range
	 * @see Enchref
	 */
	public Enchref getChoice(int index) {
		if(index < choices.size() && index >= 0) return choices.get(index);
		else return null;
	}

	/**
	 * Sets the choices of the {@link Craftable}
	 * @param choices List of {@link Enchref} options
	 * @see Enchref
	 */
	public void setChoices(List<Enchref> choices) {
		this.choices = choices;
	}

	/**
	 * Adds a {@link Enchref} to the {@link Craftable} choices
	 * @param choice {@link Enchref} to add to the {@link Craftable}
	 * @see Enchref
	 */
	public void addChoice(Enchref choice) {
		this.choices.add(choice);
	}

	/**
	 * Removes a {@link Enchref} to the {@link Craftable} choices
	 * @param choice {@link Enchref} to remove from the {@link Craftable}
	 * @see Enchref
	 */
	public void removeChoice(Enchref choice) {
		this.choices.remove(choice);
	}

	/**
	 * Updates a {@link Enchref} in the {@link Craftable} choices. Adds the new {@link Enchref} if the old {@link Enchref} is not currently in the list
	 * @param from {@link Enchref} currently in the {@link Craftable} choices to be replaced
	 * @param to {@link Enchref} to replace the <code>from</code> variable with.
	 * @see Enchref
	 */
	public void updateChoice(Enchref from, Enchref to) {
		if(choices.contains(from)) choices.set(choices.indexOf(from), to);
		else choices.add(to);
	}
	
	@Override
	public String toString() {
		return name + " [" + uuid + "]";
	}
}
