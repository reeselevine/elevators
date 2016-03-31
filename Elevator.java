/** Class representing one elevator
 *  @author Reese Levine
 */

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.HashSet;

public class Elevator {

    public int currentFloor;
    public int direction;
    public PriorityQueue<Integer> minQueue;
    public PriorityQueue<Integer> maxQueue;
    public HashMap<Integer, HashSet<Integer>> pickupRequests;

    /** Initializes an elevator, starting at floor 0 and not moving. */
    public Elevator() {
        this.currentFloor = 0;
        this.direction = 0;
        minQueue = new PriorityQueue<Integer>(10);
        maxQueue = new PriorityQueue<Integer>(10, Collections.reverseOrder());
        pickupRequests = new HashMap<Integer, HashSet<Integer>>();
    }

    /** Receives a pickup request and inserts it into the pickupRequests HashMap,
     *  as well as into the right queue.
     */
    public void receivePickupRequest(int pickupFloor, int goalFloor) {
       if (pickupRequests.containsKey(pickupFloor)) {
           pickupRequests.get(pickupFloor).add(goalFloor);
       } else {
           HashSet<Integer> set = new HashSet<Integer>();
           set.add(goalFloor);
           pickupRequests.put(pickupFloor, set);
       }
       if (pickupFloor > currentFloor) {
           minQueue.offer(pickupFloor);
       } else if (pickupFloor < currentFloor) {
           maxQueue.offer(pickupFloor);
       }
    }

    /** Updates the state of the elevator, depending on what direction it is currently
     *  going and whether or not it picks up new passengers at this floor
     */
    public void update() {
        if (pickupRequests.containsKey(currentFloor)) {
            for (int goalFloor : pickupRequests.get(currentFloor)) {
                if (goalFloor > currentFloor) {
                    minQueue.offer(goalFloor);
                } else if (goalFloor < currentFloor) {
                   maxQueue.offer(goalFloor);
                }
            }
            pickupRequests.remove(currentFloor);
        }
        if (direction > 0) {
            currentFloor += 1;
            if (currentFloor == minQueue.peek()) {
                minQueue.poll();
            }
            if (minQueue.size() == 0) {
                if (maxQueue.size() > 0) {
                    direction = -1;
                } else {
                    direction = 0;
                }
            }
        } else if (direction < 0) {
            currentFloor -= 1;
            if (currentFloor == maxQueue.peek()) {
                maxQueue.poll();
            }
            if (maxQueue.size() == 0) {
                if (minQueue.size() > 0) {
                    direction = 1;
                } else {
                    direction = 0;
                }
            }
        } else {
            if (maxQueue.size() > 0) {
                direction = -1;
            } else if (minQueue.size() > 0) {
                direction = 1;
            }
        }
    }
}
