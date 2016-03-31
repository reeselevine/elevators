Elevator Control System

My solution is written in Java, and contains two main classes, ElevatorControlSystem and Elevator.
ElevatorControlSystem does little more than maintain a record of the elevators in the system and assign
requests to each one as they come in. The elevator to assign the request to is chosen based on the direction
the elevator is traveling and whether or not it is approaching the pick up floor.

The Elevator class maintains two priority queues, one which is used if the elevator is going up (minQueue), and
one which is used if the elevator is going down (maxQueue). These queues contain both goal floors and pick up floors.
Currently, there is no way to know how many people are in a given elevator, which would require some assumptions I did not make.
It also maintains a HashMap that maps pickup floors to the goal floor of the person waiting at that floor. When an elevator reaches
a floor with a pick up request, it adds all the goal floors of people waiting at that floor to the correct queue and removes the pick up
floor from the queue. Similarily, when an elevator reaches a floor that is a goal, it removes that floor from the correct queue.

My algorithm improves on FCFS order because by using PriorityQueues, it can make efficient stops to drop off and pick up people. The request
delegator method also ensures that one elevator won't do all of the work, as elevators that are not currently being used are often assigned to
requests.

Things I would like to improve on are the request delegation, since I'm sure that could be optimized. I also would like increase my test coverage,
since I know there are things I'm missing, although basic functionality seems to be fine. Also, elevators can currently move below 0 and up to an
unspecified number, and it would probably make sense to put a cap on this number.

To run tests, simply run ./runTests.sh.

I have also provided a simple Main class that prints the state of each elevator at every iteration and waits for a request at each iteration. If there
is no request at a certain iteration, input 0 for both the pick up floor and the goal floor, and the program will continue.
