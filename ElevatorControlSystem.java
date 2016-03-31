/** Class representing the system controlling the elevators
 *  @author Reese Levine
 */

import java.util.ArrayList;

public class ElevatorControlSystem {

    public ArrayList<Elevator> elevators;

    /** Initializes a new Elevator Control System, with the number
     *  of elevators specified by numElevators
     */
    public ElevatorControlSystem(int numElevators) {
        elevators = new ArrayList<Elevator>();
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator());
        }
    }

    /** Updates the system, leaving most of the work to the actual elevators */
    public void update() {
        for (Elevator elevator : elevators) {
            elevator.update();
        }
    }

    /** Prints out the current floor and direction of each elevator */
    public void printUpdate() {
        int i = 1;
        for (Elevator elevator : elevators) {
            String direction;
            if (elevator.direction == 1) {
                direction = "up";
            } else if (elevator.direction == -1) {
                direction = "down";
            } else {
                direction = "nowhere";
            }
            System.out.println("Elevator " + i + " is on floor " + elevator.currentFloor + " and is moving " + direction);
            i++;
        }
    }

    /** Iterates through the elevators in the system and chooses which one to
     * assign this pick up too, based on elevator direction and current floor.
     */
    public void delegatePickup(int pickupFloor, int goalFloor) {
        int direction = (goalFloor - pickupFloor) / Math.abs(goalFloor - pickupFloor);
        Elevator bestElevator = elevators.get(0);
        for (Elevator elevator : elevators) {
            if (Math.abs(elevator.currentFloor - pickupFloor) < Math.abs(bestElevator.currentFloor - pickupFloor) ||
                    direction * (elevator.currentFloor - pickupFloor) <= 0 && direction * (bestElevator.currentFloor - pickupFloor) > 0) {
                bestElevator = elevator;
            }
        }
        bestElevator.receivePickupRequest(pickupFloor, goalFloor);
    }
} 
