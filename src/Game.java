/**
 * Created by Lia Bogoev using IntelliJ
 * 4/13/14
 * CS 2410
 */
public class Game {

    // Rod subclass
    public class Rod {
        public int diskArray[];
        public int ID;
        public int top;

        // constructor
        public Rod(int disksHeld, int num){
            this.diskArray = new int[disksHeld];
            this.ID = num;
            this.top = disksHeld - 1; // e.g., 5 disks, top index is 4
        }
    }
    public int numberOfDisks;
    public Rod rod1, rod2, rod3;

    public Game(int numberOfDisks){
        this.numberOfDisks = numberOfDisks;

        rod1 = new Rod(numberOfDisks,1);
        rod2 = new Rod(numberOfDisks,2);
        rod3 = new Rod(numberOfDisks,3);

        for(int i = 0; i < numberOfDisks; ++i){
            // initialize the game
            rod1.diskArray[i] = (numberOfDisks - i);
            rod2.diskArray[i] = 0;
            rod3.diskArray[i] = 0;
        }

    }

    public void print(){
        for(int i = (numberOfDisks - 1); i >= 0; i -= 1){
            System.out.println(rod1.diskArray[i] + " " + rod2.diskArray[i] + " " + rod3.diskArray[i]);
        }
    }

    public void move(Rod origin, Rod destination){
        if(origin.diskArray[origin.top] > destination.diskArray[destination.top]){
            // invalid move
        }
        else{
            destination.top++;  // move to the next blank spot in the array
            destination.diskArray[destination.top] = origin.diskArray[origin.top]; // set it equal to the disk in the top spot of origin
            origin.diskArray[origin.top] = 0;   // set the top spot of origin to 0
            origin.top--;           // move the top tracker to the next full spot
        }
    }
}
