package picard.util;

import java.util.HashSet;

/**
 * Created by alexandr on 10.10.15.
 */
public class LoopArray {
    private final int _type;
    private final int _length;
    private int[] _pass;
    private int[] _array;
    private HashSet<String>[] _arrayHS;

    public LoopArray(int length,int type) {
        _type = type;
        _length = length;
        _pass =new int[_length];
        if(_type==0){
            _array=new int[_length];
        } else
            _arrayHS=new HashSet[_length];
    }

    public void incriment(int i) {
        if(_type==0){
            _array[getIndex(i)]++;
        }
    }

    public boolean add(int i, String readName) {
        if(_type!=0){
            return _arrayHS[getIndex(i)].add(readName);
        } else {
            return false;
        }
    }

    public int get(int i) {
        return _array[i%_length];
    }
    private int getIndex(int i){
        int index = i%_length;
        int pass = 1/_length;
        if (_pass[index]!=pass){
            if(_type==0){
                _array[index]=0;
            } else {
                _arrayHS[index] = new HashSet<>();
            }

        }
        return index;
    }


    public HashSet<String> getHS(int i) {
        return _arrayHS[(i%_length)];

    }
}
