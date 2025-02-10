package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread{

    private int[] array;
    private int maxNumber;

    public MaxThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        System.out.println("INFO: Thread " + getName() + " started");
        maxNumber = Arrays.stream(array).max().getAsInt();
        System.out.println("INFO: Thread " + getName() + " finished");
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}
// END
