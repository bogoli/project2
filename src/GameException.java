/**
 * Created by Lia Bogoev using IntelliJ
 * 4/19/14
 * CS 2410
 */
public class GameException extends Exception{
    public GameException(){
        super("Empty tower!");
    }

    public GameException(String message){
        super(message);
    }
}

