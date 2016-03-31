/** The main class, where the program is started and controlled
 *  @author Reese Levine
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            printUsage();
            System.exit(1);
        }
        int numElevators = Integer.parseInt(args[0]);
        if (numElevators < 1 || numElevators > 16) {
            printUsage();
            System.exit(1);
        }
        Scanner sc = new Scanner(System.in);
        ElevatorControlSystem system = new ElevatorControlSystem(numElevators);
        int time = 0;
        while (true) {
            System.out.println("Time " + time);
            system.update();
            system.printUpdate();
            System.out.println();
            System.out.print("Next pickup floor: ");
            int pickupFloor = sc.nextInt();
            System.out.println();
            System.out.print("Next goal floor: ");
            int goalFloor = sc.nextInt();
            System.out.println();
            if (pickupFloor != 0 || goalFloor != 0) {
                system.delegatePickup(pickupFloor, goalFloor);
            }
            time++;
        }
    }

    public static void printUsage() {
        System.out.println("Usage: java Main [0-16]");
        System.out.println("The number corresponds to the numbers of elevators in this system");
    }
}
