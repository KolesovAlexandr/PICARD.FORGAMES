package picard.util;

import java.util.Arrays;

/**
 * Created by alexandr on 10.10.15.
 */
public class ShiftArray {

    private int READ_LENGHT = 200; //значение по-умолчанию для стандартной длины рида в 150 оснований. 200 берём для верности.
    private int offset = 0;        //компенсирует позицию локуса относительно внутренних массивов

    private int _commonLength;
    private int[] _arrayBaseq;
    private int[] _arrayOverlap;
    private int[] _readNameSize;

    public ShiftArray(final int length){
        _commonLength = length;
        _arrayBaseq = new int[_commonLength];
        _arrayOverlap = new int[_commonLength];
        _readNameSize = new int[_commonLength];
    }

    public ShiftArray(final int length, int readLength) {
        this(length);
        READ_LENGHT = readLength + 50;  //+50 для верности
    }

    public void incrimentBaseQ(int index) {
        _arrayBaseq[index - offset]++;
    }

    //главная проверка, что текущее положение локуса не выходит за пределы массивов
    public void checkOutOfBounds(int loc){
        if (loc - offset + READ_LENGHT >= _commonLength){
            changeArray(loc);
        }
    }

    public void incrimentOverlap(int index) {
        _arrayOverlap[index - offset]++;
    }


    private void changeArray(int locusPos) {

        System.arraycopy(_arrayOverlap, locusPos - offset, _arrayOverlap, 0, _commonLength - locusPos + offset);
        System.arraycopy(_arrayBaseq, locusPos - offset, _arrayBaseq, 0, _commonLength - locusPos + offset);
        System.arraycopy(_readNameSize, locusPos - offset, _readNameSize, 0, _commonLength - locusPos + offset);

        Arrays.fill(_arrayOverlap, _commonLength - locusPos + offset, _commonLength, 0);
        Arrays.fill(_arrayBaseq, _commonLength - locusPos + offset, _commonLength, 0);
        Arrays.fill(_readNameSize, _commonLength - locusPos + offset, _commonLength, 0);

        offset = locusPos;
    }


    public int getBaseQ(int i) {
        return _arrayBaseq[i - offset];
    }

    public int getOverlap(int i) {
        return _arrayOverlap[i - offset];
    }

    public int getReadNameSize(int i) {
        return _readNameSize[i - offset];
    }


    public void incrimentreadNameSize(int index) {
        _readNameSize[index - offset]++;
    }

    public void clear() {
        _arrayBaseq = new int[_commonLength];
        _readNameSize = new int[_commonLength];
        _arrayOverlap = new int[_commonLength];
        offset = 0;
    }

}