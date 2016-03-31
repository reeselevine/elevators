/** Class representing one elevator
 *  @author Reese Levine
 */
public class Elevator {

    public int currentFloor;
    public boolean goingUp;

    /** Initializes an elevator, starting at floor 0 and going up */
    public Elevator() {
        this.currentFloor = 0;
        this.goingUp = true;
    }
}
