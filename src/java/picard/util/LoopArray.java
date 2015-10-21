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
//        if (i == 1150) {
//            System.out.println();
//        }
        int index = i % _length;
        if (_pointer == index) {
            _arrayBaseq[_pointer] = _arrayOverlap[_pointer] = 0;
            _readNameSize[_pointer] = 0;
            if (++_pointer == _length) {
                _pointer = 0;
            }
        }
        return index;
    }

    public void incrimentOverlap(int i) {
        _arrayOverlap[i]++;
    }

//    public boolean add(int i, String readName) {
//        int index = i;
//
//        if (_readNames[index] == null)
//            _readNames[index] = new HashSet<>();
//        return _readNames[index].add(readName);
////        return true;
//    }


    public int getBaseQ(int i) {
        return _arrayBaseq[i];
    }

    public int getOverlap(int i) {
        return _arrayOverlap[i];
    }

    public int getReadNameSize(int i) {
        return _readNameSize[i];
    }

//    public HashSet<String> getReadNames(int i) {
//        return _readNames[i];
//    }

    public void incrimentreadNameSize(final int i) {
        _readNameSize[i]++;
    }

//    public int getIndex(int i) {
//        int index = i % _length;
//        int pass = i / _length;
//        if (_pass[index] != pass) {
//            _arrayBaseq[index] = 0;
//            _arrayOverlap[index] = 0;
//            _readNames[index] = new HashSet<>();
//            _pass[index] = pass;
//        }
//        return index;
//    }


}
