import static org.junit.Assert.*;
import org.junit.Test;

/** Tests the Elevator Control System
 *  @author Reese Levine
 */
public class ElevatorControlSystemTest {

    @Test
    public void testSingleElevatorSinglePickup() {
        Elevator e = new Elevator();
        e.receivePickupRequest(4, 0);
        for (int i = 0; i < 5; i++) {
            e.update();
        }
        assertEquals(4, e.currentFloor);
        for (int i = 0; i < 4; i++) {
            e.update();
            assertEquals(1, e.maxQueue.size());
        }
        e.update();
        assertEquals(0, e.currentFloor);
        e.update();
        assertEquals(0, e.currentFloor);
    }

    @Test
    public void testSingleElevatorMultiplePickup() {
        Elevator e = new Elevator();
        e.receivePickupRequest(5, 0);
        for (int i = 0; i < 3; i++) {
            e.update();
        }
        e.receivePickupRequest(3, 8);
        e.update();
        assertEquals(1, e.minQueue.size());
        assertEquals(1, e.direction);
        e.update();
        e.update();
        e.update();
        assertEquals(1, e.maxQueue.size());
        for (int i = 0; i < 2; i++) {
            e.update();
        }
        assertEquals(8, e.currentFloor);
        for (int i = 0; i < 9; i++) {
            e.update();
        }
        assertEquals(0, e.currentFloor);
        assertEquals(0, e.direction);
    }

    @Test
    public void testElevatorControlSystem() {
        ElevatorControlSystem system = new ElevatorControlSystem(8);
        assertEquals(8, system.elevators.size());
        system.delegatePickup(4, 5);
        for (int i = 0; i < 5; i++) {
            system.update();
        }
        assertEquals(4, system.elevators.get(0).currentFloor);
        system.update();
        assertEquals(1, system.elevators.get(0).direction);
    }
}
