package structures;

import static java.lang.reflect.Array.newInstance;

import java.util.NoSuchElementException;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.
   */
  @SuppressWarnings("unchecked")
  @Override
  public AssociativeArray<K, V> clone() {
    AssociativeArray newArray= new AssociativeArray<>();
    newArray.size=this.size;
    for(int i =0;i<this.size;i++){
      if(this.pairs[i]!=null){
          newArray.pairs[i] = new KVPair<K,V>(this.pairs[i].key,this.pairs[i].value);
    }
    }
    return newArray;


    /* 
      try {
          AssociativeArray<K, V> clone = (AssociativeArray<K, V>) super.clone();
          clone.pairs = new KVPair[this.pairs.length];
          for (int i = 0; i < this.pairs.length; i++) {
              // Assuming KVPair has a proper copy constructor or a clone method
              clone.pairs[i] = (this.pairs[i] != null) ? this.pairs[i].clone() : null;
          }
          return clone;
      } catch (CloneNotSupportedException e) {
          throw new AssertionError(); // Can never happen if the object is Cloneable
      }

      */
  }

  /**
   * Convert the array to a string.
   */
  @Override
  public String toString() {
      if (this.size == 0) {
          return "{}";
      }
  
      StringBuilder sb = new StringBuilder();
      sb.append("{ ");
      for (int i = 0; i < this.size; i++) {
          sb.append(this.pairs[i].key).append(": ").append(this.pairs[i].value);
          if (i < this.size - 1) {
              sb.append(", ");
          }
      }
      sb.append(" }");
      return sb.toString();
  }

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
/* 
   public void set(K key, V value){
    map.put(key, value);
}
*/
   
  public void set(K key, V value) throws NullKeyException {
    if (key == null) {
      throw new NullKeyException("Key cannot be null");
  }
    for(int i =0;i<this.size;i++){
      if(this.pairs[i].key.equals(key)){
        this.pairs[i].value=value;
        return;
      }
    }
    if (this.size == this.pairs.length) {
      this.expand();
  }

  this.pairs[this.size] = new KVPair<>(key, value);
  this.size++;

  } // set(K,V)


  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key is null or does not 
   *                              appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
    for(int i =0;i<this.size;i++){
      if(this.pairs[i].key.equals(key)){
        return this.pairs[i].value;
    }
  }
     throw new KeyNotFoundException ("Key not found: " + key);
    
    } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */
  //@SuppressWarnings("unused")
  public boolean hasKey(K key) {
    for(int i =0;i<this.size;i++){
      if(this.pairs[i].key.equals(key)){
        return true;
      } 
    }
    return false;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    for (int i = 0; i < this.size; i++) {
        if (this.pairs[i].key.equals(key)) {
            for (int j = i; j < this.size - 1; j++) {
                this.pairs[j] = this.pairs[j + 1];
            }
            this.size--;
            return;
        }
    }
}


  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  
  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    throw new KeyNotFoundException();   // STUB
  } // find(K)

} // class AssociativeArray
