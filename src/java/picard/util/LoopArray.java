package picard.util;

/**
 * Created by alexandr on 10.10.15.
 */
public class LoopArray {
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

    public void incrimentBaseQ(int i) {
        _arrayBaseq[i]++;
    }

    public int shiftPointer(int i) {
        int index = i % _length;
//        if (i > 999) if (index > _pointer) {
//            System.out.println();
//        }
//        if (_pointer < index) {
//            for (int j = _pointer; j <= index; j++) {
//                _arrayBaseq[j] = _arrayOverlap[j] = 0;
//                _readNameSize[j] = 0;
//            }
//            if (index + 1 > _length) {
//                _pointer = 0;
//            } else _pointer = index;
//            return index;
//
//        }
        if (_pointer == index) {
            _arrayBaseq[_pointer] = _arrayOverlap[_pointer] = 0;
            _readNameSize[_pointer] = 0;
            if (++_pointer == _length) {
                _pointer = 0;
            }
        }
        return index;
    }
//
    public void incrimentOverlap(int i) {
        _arrayOverlap[i]++;
    }


    public int getBaseQ(int i) {
        return _arrayBaseq[i];
    }

    public int getOverlap(int i) {
        return _arrayOverlap[i];
    }

    public int getReadNameSize(int i) {
        return _readNameSize[i];
    }


    public void incrimentreadNameSize(final int i) {
        _readNameSize[i]++;
    }

    public void clear() {
        _arrayBaseq = new int[_length];
        _readNameSize = new int[_length];
        _arrayOverlap = new int[_length];
        _pointer = 0;
    }

}