package picard.util;

import java.util.Arrays;

/**
 * Created by alexandr on 10.10.15.
 */
public class LoopArray {

    public final static int READ_LENGHT = 200;
    private int q = 0;

    private final int _length;

    private int[] _arrayBaseq;
    private int _pointer;
    private int[] _arrayOverlap;
    private int[] _readNameSize;

    public LoopArray(final int length, int pointer) {
        _pointer = pointer;
        _length = length;
        _arrayBaseq = new int[_length];
        _arrayOverlap = new int[_length];
        _readNameSize = new int[_length];
    }

    public void incrimentBaseQ(int index) {

        if (index - q >= _length){
            changeArray(index);
        }

        _arrayBaseq[index - q]++;
    }


    //TODO: сделать превентивную перепись массивов: если locusPos + rec.length > _length, то совершить перепись.
    // Т.е переполнение массива не должно произойти впринципе.

    public void shiftIfFindN(int locusPos){

        //++q;
        if (q != 0) ++q; //возможное решение: если локус с качеством N найден, но переписи массивов не было,
                         //а значит и q == 0, то и делать компенсацию q++ делать не нужно MODIFICATION V2
    }

    public void checkNOutOfBounds(int index){
        if (index - q >= _length){
            changeArray(index);
        }
    }

    public void checkOutOfBoundsRead(int loc){
        if (loc - q + READ_LENGHT >= _length){
            changeArray(loc);
        }
    }

    public void incrimentOverlap(int index) {

        if (index - q >= _length){
            changeArray(index);
        }

        _arrayOverlap[index - q]++;
    }

    private void changeArray(int locusPos) {

        System.arraycopy(_arrayOverlap, locusPos - q, _arrayOverlap, 0, _length - locusPos + q);
        System.arraycopy(_arrayBaseq, locusPos - q, _arrayBaseq, 0, _length - locusPos + q);
        System.arraycopy(_readNameSize, locusPos - q, _readNameSize, 0, _length - locusPos + q);

        Arrays.fill(_arrayOverlap, _length - locusPos + q, _length, 0);
        Arrays.fill(_arrayBaseq, _length - locusPos + q, _length, 0);
        Arrays.fill(_readNameSize, _length - locusPos + q, _length, 0);

        q = locusPos;
    }


    public int getBaseQ(int i) {

        if (i - q >= 100000){
            int h = 0;
        }
        try {
            int y = _arrayBaseq[i - q];
        }catch (IndexOutOfBoundsException e){
            System.out.println("ERRORRRR!!!!!!!!!!!!!!!!!");
        }
        return _arrayBaseq[i - q];
    }

    public int getOverlap(int i) {
        return _arrayOverlap[i - q];
    }

    public int getReadNameSize(int i) {
        return _readNameSize[i - q];
    }


    public void incrimentreadNameSize(int index) {

        if (index - q >= _length){
            changeArray(index);
        }

        _readNameSize[index - q]++;
    }

    public void clear() {
        _arrayBaseq = new int[_length];
        _readNameSize = new int[_length];
        _arrayOverlap = new int[_length];
        _pointer = 0;
        q = 0;
    }

}