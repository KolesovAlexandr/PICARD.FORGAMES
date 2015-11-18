package picard.util;

/**
 * Created by alexandr on 10.10.15.
 */
public class LoopArray {
    private final int length;
    private final int firstPosition;
    private int[] arrayBaseq;
    private int[] arrayOverlap;
    private int[] readNameSize;
    private int pointer;
    private int shift = 0;


    public LoopArray(final int length, int firstPosition) {
        this.pointer = firstPosition;
        this.firstPosition = firstPosition;
        this.length = length;
        arrayBaseq = new int[this.length];
        arrayOverlap = new int[this.length];
        readNameSize = new int[this.length];
    }

    public void incrimentBaseQ(int i) {
        arrayBaseq[i]++;
    }

    public int getIndex(int i) {
        int index;
        if (i - shift >= length) {

            shift = (i / length) * length;
        }

        index = i - shift;

        if (index < 0) {
            index += length;
        }

        return index;
    }

    public void incrimentOverlap(int i) {
        arrayOverlap[i]++;
    }


    public int getBaseQ(int i) {
        return arrayBaseq[i];
    }

    public int getOverlap(int i) {
        return arrayOverlap[i];
    }

    public int getReadNameSize(int i) {
        return readNameSize[i];
    }


    public void incrimentreadNameSize(final int i) {
        readNameSize[i]++;

    }

    public void clear() {
        arrayBaseq = new int[length];
        readNameSize = new int[length];
        arrayOverlap = new int[length];
        pointer = firstPosition;
        shift = 0;
    }

    public void shiftPointer(final int position) {
        resetCounters();
        pointer = position;
    }

    public void resetCounters() {
        arrayBaseq[pointer] = 0;
        arrayOverlap[pointer] = 0;
        readNameSize[pointer] = 0;
    }
}