package picard.util;

/**
 * Created by alexandr on 10.10.15.
 */
public class LoopArray {
    private final int _length;

    private int[] _arrayBaseq;
    private int _wPointer;
    private int _rPointer;
    private int[] _arrayOverlap;
    private int[] _readNameSize;
    private int shift = 0;


    public LoopArray(final int length, int pointer) {
        _wPointer = pointer;
        _rPointer = pointer;
        _length = length;
        _arrayBaseq = new int[_length];
        _arrayOverlap = new int[_length];
        _readNameSize = new int[_length];
    }

    public void incrimentBaseQ(int i) {
        shiftWPointer(i);
        _arrayBaseq[i]++;
    }

    public int getIndex(int i) {
//        int index = i % _length;
        int index;
        if (i - shift >= _length) {

            shift = (i / _length) * _length;
        }

        index = i - shift;

        if (index < 0) {
            index += _length;
        }

//        if (index == -4999){
//            System.out.println();
//        }


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

//
//        if (_wPointer == index) {
//            getIndex();

//        }

        return index;
    }

//    public void getIndex() {
//        _arrayBaseq[_wPointer] = 0;
//        _arrayOverlap[_wPointer] = 0;
//        _readNameSize[_wPointer] = 0;
//        if (++_wPointer == _length) {
//            _wPointer = 0;
//        }
//    }

    //
    public void incrimentOverlap(int i) {

        shiftWPointer(i);
        _arrayOverlap[i]++;
    }

    private void shiftWPointer(final int i) {

        if (_rPointer < _wPointer) {
            if ((i >= 0 && i < _rPointer) || (i > _wPointer && i < _length)) {
                resetAndShift(i);
            }
        } else if (_rPointer > _wPointer) {
            if (i > _wPointer && i < _rPointer) {
                resetAndShift(i);

            }
        } else {
            if (i > _rPointer) {
                _wPointer = i;
            }

        }
//        if (_rPointer < _wPointer) {
//            if ((i >= 0 && i < _rPointer) || (i >= _wPointer && i < _length)) {
//                array[i] = 0;
//                _wPointer = i;
//            }
//
//        } else if (_rPointer > _wPointer) {
//            if (i > _wPointer && i < _rPointer) {
//                array[i] = 0;
//                _wPointer = i;
//            }
//
//        } else {
//            if (i==_wPointer){
//                array[i] = 0;
//            }
//            _wPointer = i;
//        }

    }

    private void resetAndShift(final int i) {
        _arrayBaseq[i] = 0;
        _arrayOverlap[i] = 0;
        _readNameSize[i] = 0;
        _wPointer = i;
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
        shiftWPointer(i);
        _readNameSize[i]++;

    }

    public void clear() {
        _arrayBaseq = new int[_length];
        _readNameSize = new int[_length];
        _arrayOverlap = new int[_length];
        _wPointer = 1;
        _rPointer = 1;
        shift = 0;
    }

    public void shiftPointers(final int position) {
//        TODO при большом перескоке не сдвигается
        resetCounters();

        if (_rPointer == _wPointer) {
            _wPointer = position;
        }
        _rPointer = position;
    }

    public void resetCounters() {
        _arrayBaseq[_rPointer] = 0;
        _arrayOverlap[_rPointer] = 0;
        _readNameSize[_rPointer] = 0;
    }
}