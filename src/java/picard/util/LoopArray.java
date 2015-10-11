package picard.util;

import java.util.HashSet;

/**
 * Created by alexandr on 10.10.15.
 */
public class LoopArray {
    private final int _length;

    private int[] _arrayBaseq;
    private int[] _arrayOverlap;
    private HashSet<String>[] _readNames;
    private int[] _pass;
    private HashSet<String>[] _arrayHS;

    public LoopArray(int length) {
        _length = length;
        _arrayBaseq =new int[_length];
        _arrayOverlap = new int[_length];
        _readNames = new HashSet[_length];
        _pass = new int[_length];
    }

    public void incrimentBaseQ(int i) {
        _arrayBaseq[i]++;
    }

    public void incrimentOverlap(int i) {
        _arrayOverlap[i]++;
    }

    public boolean add(int i,String readName) {
        if (_readNames[i]==null)
            _readNames[i]=new HashSet<>();
        return _readNames[i].add(readName);
    }





    public int getBaseQ(int i) {
        return _arrayBaseq[i];
    }
    public int getOverlap(int i) {
        return _arrayOverlap[i];
    }
    public HashSet<String> getReadNames(int i) {
        return _readNames[i];
    }

    public int getIndex(int i) {
        if(i==1000)
        {
            System.out.println();
        }
        int index = i % _length;
        int pass = i / _length;
        if(_pass[index]!= pass){
            _arrayBaseq[index]=0;
            _arrayOverlap[index]=0;
            _readNames[index]=new HashSet<>();
            _pass[index]=pass;
        }
        return index;
    }



}
