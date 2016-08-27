package algorithms.search;

public class State<T> implements Comparable<State<T>> {
	private State<T> cameFrom;
	private double cost;
	private T value;
	private String key;
	
	public State(String key) {
		this.key = key;
	}
	public State(State<T> object){
		this.key = object.getKey();
		this.cost = object.getCost();
		this.value = object.getValue();
		this.cameFrom = object.getCameFrom();
	}
	public State(T object){
		this.key = "";
		this.cost = 0;
		this.value = object;
		this.cameFrom = null;
	}
	
	public double getCost() {
		return cost;
	}
	public T getValue() {
		return value;
	}
	public State<T> getCameFrom() {
		return cameFrom;
	}
	public String getKey() {
		return key;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
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