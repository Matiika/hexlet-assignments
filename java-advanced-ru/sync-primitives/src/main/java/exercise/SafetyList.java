package exercise;

class SafetyList {
    // BEGIN
    private int[] array = new int[10];
    private int pointer = 0;

    public synchronized void add(int number) {
        if (pointer == array.length) {
            resize(array.length * 2);
        }
        array[pointer] = number;
        pointer++;
    }

    private void resize(int newLength) {
        int[] newArray = new int[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    public int get(int index) {
        return array[index];
    }

    public int getSize() {
        return pointer;
    }

    // END
}
