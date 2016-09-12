package algorithms.search;

public class State<T> implements Comparable<State<T>> {
	private State<T> cameFrom;
	private double cost;
	private T value;
	private String key;
	
	/**
	 * Constructor
	 * @param key String This is the State key
	 */
	public State(String key) {
		this.key = key;
	}
	/**
	 * Constructor
	 * @param object State<T> This is the State object
	 */
	public State(State<T> object){
		this.key = object.getKey();
		this.cost = object.getCost();
		this.value = object.getValue();
		this.cameFrom = object.getCameFrom();
	}
	/**
	 * Constructor
	 * @param object T this is the template object
	 */
	public State(T object){
		this.key = "";
		this.cost = 0;
		this.value = object;
		this.cameFrom = null;
	}
	
	/**
	 * Cost getter
	 * @return double This returns the cost
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * Template object getter
	 * @return T This returns the template object
	 */
	public T getValue() {
		return value;
	}
	/**
	 * Previous State getter
	 * @return State This returns the previous State
	 */
	public State<T> getCameFrom() {
		return cameFrom;
	}
	/**
	 * Key getter
	 * @return String This returns the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Cost setter
	 * @param cost double This the the cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * Value setter
	 * @param value T This the the template object
	 */
	public void setValue(T value) {
		this.value = value;
	}
	/**
	 * State setter
	 * @param cameFrom State this the previous State
	 */
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	/**
	 * Key setter
	 * @param key String This is the key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if(obj.getClass() == this.getClass()) 
		{
			return value.equals(((State<T>)obj).value);
		}
		return false;
	}
	@Override
	public int compareTo(State<T> s) {
		return (int)(this.getCost() - s.getCost());	
		// return > 0 if this > s
		//        < 0 if this < s
		//        = 0 if this == s
	}
}