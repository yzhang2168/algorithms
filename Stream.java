package algorithms;

public interface Stream<E> {

	// returns null if the stream ends
	public E getNext();
	
}
