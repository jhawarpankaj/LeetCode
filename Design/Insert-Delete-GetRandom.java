/*

Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements (it's guaranteed that at least one element exists when this method is called). 
Each element must have the same probability of being returned.
 
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
*/

// No Duplicate elements

class RandomizedSet {
    
    Map<Integer, Integer> map;
    List<Integer> list;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;        
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        mapIndexUpdate(list.get(list.size() - 1), index);
        swap(index, list.size() - 1);
        list.remove(list.size() - 1);
        map.remove(val);        
        return true;
    }
    
    void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    
    void mapIndexUpdate(int num, int newIndex) {
        map.put(num, newIndex);
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int r = rand.nextInt(list.size());
        return list.get(r - 1);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

// Duplicates allowed

class RandomizedCollection {
    
    Map<Integer, LinkedHashSet<Integer>> map;
    List<Integer> list;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {        
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        int ind = list.size() - 1;
        if (!map.containsKey(val)) {            
            map.computeIfAbsent(val, x -> new LinkedHashSet<>()).add(ind);
            return true;
        } else {
            map.get(val).add(ind);
            return false;
        }        
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        int last = list.size() - 1;
        if (!map.containsKey(val)) return false;
        int ind = getAnIndexFromMap(val);
        deleteIndexFromMap(val, ind);
        if (ind == last) {
            list.remove(last);
            return true;
        }
        int lastVal = list.get(last);
        updateIndexInMap(lastVal, last, ind);
        swapElemInList(last, ind);
        list.remove(last);
        return true;
    }
    
    int getAnIndexFromMap(int val) {
        return map.get(val).iterator().next();
    }
    
    void deleteIndexFromMap(int val, int ind) {
        map.get(val).remove(ind);
        if (map.get(val).isEmpty()) map.remove(val);
    }
    
    void updateIndexInMap(int val, int oldIndex, int newIndex) {
        map.get(val).remove(oldIndex);
        map.get(val).add(newIndex);
    }
    
    void swapElemInList(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
