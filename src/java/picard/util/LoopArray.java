package picard.util;

import java.util.Arrays;

/**
 * Created by alexandr on 10.10.15.
 */
public class LoopArray {

    private int READ_LENGHT = 200; //значение по-умолчанию для стандартной длины рида в 150 оснований. 200 берём для верности.
    private int offset = 0;

    private int _commonLength;
    private int[] _arrayBaseq;
    private int[] _arrayOverlap;
    private int[] _readNameSize;

    public LoopArray(final int length){
        _commonLength = length;
        _arrayBaseq = new int[_commonLength];
        _arrayOverlap = new int[_commonLength];
        _readNameSize = new int[_commonLength];
    }

    public LoopArray(final int length, int readLength) {
        this(length);
        READ_LENGHT = readLength + 50;  //+50 для верности
    }

    public void incrimentBaseQ(int index) {

        /**
        if (index - offset >= _commonLength){
            changeArray(index);
        }
         **/

        _arrayBaseq[index - offset]++;
    }


    //TODO: сделать превентивную перепись массивов: если locusPos + rec.length > _commonLength, то совершить перепись.
    // Т.е переполнение массива не должно произойти впринципе.

    public void shiftIfFindN(int locusPos){

        //++offset;
        if (offset != 0) ++offset; //возможное решение: если локус с качеством N найден, но переписи массивов не было,
                         //а значит и offset == 0, то и делать компенсацию offset++ делать не нужно MODIFICATION V2
    }

    public void checkNOutOfBounds(int index){
        if (index - offset >= _commonLength){
            changeArray(index);
        }
    }

    public void checkOutOfBoundsRead(int loc){
        if (loc - offset + READ_LENGHT >= _commonLength){
            changeArray(loc);
        }
    }

    public void incrimentOverlap(int index) {
        /**
        if (index - offset >= _commonLength){
            changeArray(index);
        }
        **/
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
        /**
        if (index - offset >= _commonLength){
            changeArray(index);
        }
        **/
        _readNameSize[index - offset]++;
    }

    public void clear() {
        _arrayBaseq = new int[_commonLength];
        _readNameSize = new int[_commonLength];
        _arrayOverlap = new int[_commonLength];
        offset = 0;
    }

}