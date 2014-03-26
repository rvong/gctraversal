/**
 * @author Richard Vong 65674401
 * @date January 28, 2014
 * Project 2
 * Part 1: GC Simulator
 * 
 * Usage: assign, createObject, readObject, writeObject, and gc are all
 *  public static void methods. Call and parameters are according to spec:
 *  
 * 	public static void assign(String aName, String bName, Object o): (aName:o) is added to stackRef
 *	public static void createObject(String aName, Object o): o is added to set of all created objects
 *	public static void readObject(String bName, String aName, Object o): (bName:o) is added to stackRef
 *	public static void writeObject(String aName, String bName, String fieldname, Object oa, Object ob): Store ob into fieldname of oa
 *	public static void gc(): Run the garbage collector, print out unreachable objects.
 *
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

public class GCSimulator {
	static Map<String, Object> stackRef = new HashMap<String, Object>();
	static Map<Object, Map<String, Object>> heapRef = new HashMap<Object, Map<String, Object>>();
	static Set<Object> allSet = new HashSet<Object>();
	static Set<Object> visitedSet = new HashSet<Object>();
	static Queue<Object> workQueue = new LinkedList<Object>();
	static int counter = 1;
	
	// aName and bName represent the variable names “a” and “b”, respectively;  o denotes the object referenced by a and b
	public static void assign(String aName, String bName, Object o) {
		stackRef.put(aName, o);
	}

	// aName is the name of variable “a”, and o represents the newly created object
	public static void createObject(String aName, Object o) {
		allSet.add(o);
		stackRef.put(aName, o);
	}

	// bName and aName represent the two variables “b” and “a”, and o denotes the heap object referenced by b
	public static void readObject(String bName, String aName, Object o) {
		stackRef.put(bName, o);
	}

	// bName and aName represent the two variables “b” and “a”, fieldname denotes the name of the field f, 
    // oa denotes the heap object referenced by a, and ob denotes the heap object referenced by b
	public static void writeObject(String aName, String bName, String fieldname, Object oa, Object ob) {
		if (heapRef.containsKey(oa)) {		// If it's contained, update it.
			(heapRef.get(oa)).put(fieldname, ob);
		} else {							// If it's not contained, create and add it.
			Map<String, Object> myMap = new HashMap<String, Object>();
			myMap.put(fieldname, ob);
			heapRef.put(oa, myMap);
		}
	}
	
	public static void gc() { // a gc method that will be explicitly called
		for (Object o : stackRef.values())
			workQueue.add(o);

		while (! workQueue.isEmpty()) {
			Object o = workQueue.poll();
			visitedSet.add(o);
			if (heapRef.containsKey(o)) {
				for (Object p : (heapRef.get(o)).values())
					if (! visitedSet.contains(p)) workQueue.add(p);
			}
		}
		
		printResults();
	}
	
	private static void printResults() {
		System.out.println("\n\nGC#" + counter++);
		System.out.println("\nThe following objects are unreachable:\n");
		for (Iterator<Object> it = allSet.iterator(); it.hasNext();) {
			Object o = it.next();
			if (! visitedSet.contains(o)) System.out.println("Object " + o + ",");
		}
	}
}
