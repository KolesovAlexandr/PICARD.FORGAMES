package picard.util;

import java.util.HashSet;

/**
 * Created by alexandr on 10.10.15.
 */
public class LoopArray {
    private final int _length;
    private int _oldShiftIndex;

    private int[] _arrayBaseq;
    private int _pointer;
    private int _shiftIndex;
    private int[] _arrayOverlap;
    private HashSet<String>[] _readNames;
//    private int[] _pass;

    public LoopArray(int length) {
        _pointer = 0;
        _length = length;
        _shiftIndex = 0;
        _oldShiftIndex = 0;
        _arrayBaseq = new int[_length];
        _arrayOverlap = new int[_length];
        _readNames = new HashSet[_length];
//        _pass = new int[_length];
    }

    public void incrimentBaseQ(int i) {

        _arrayBaseq[shiftPointer(i)]++;

    }

    private int shiftPointer(int i) {

        if (i == _pointer) {
            if (++_pointer == _shiftIndex +_length) {
                _oldShiftIndex = _shiftIndex;
                _shiftIndex += _length;
                _pointer = 0;
            }
            _arrayBaseq[i- _shiftIndex] = 0;
        }
        int index;
        if (i >= _shiftIndex) {
            index = i - _shiftIndex;
        } else {
            index = i - _oldShiftIndex;
        }
        return index;
    }

    public void incrimentOverlap(int i) {
        _arrayOverlap[shiftPointer(i)]++;
    }

    public boolean add(int i, String readName) {
        int index = shiftPointer(i);
        if (_readNames[index] == null)
            _readNames[index] = new HashSet<>();
        return _readNames[index].add(readName);
    }


    public int getBaseQ(int i) {
        return _arrayBaseq[shiftPointer(i)];
    }

    public int getOverlap(int i) {
        return _arrayOverlap[shiftPointer(i)];
    }

    public HashSet<String> getReadNames(int i) {
        return _readNames[shiftPointer(i)];
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
