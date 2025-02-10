package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread{
    private int[] array;
    private int minNumber;

    public MinThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        System.out.println("INFO: Thread " + getName() + " started");
        minNumber = Arrays.stream(array).min().getAsInt();
        System.out.println("INFO: Thread " + getName() + " finished");
    }

    public int getMinNumber() {
        return minNumber;
    }
}
// END
