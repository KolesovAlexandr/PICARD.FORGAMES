package htsjdk.samtools.util;

import htsjdk.samtools.SamReader;

/**
 * Created by Aleksandr_Kolesov on 10/22/2015.
 */
public class SamLocusIteratorCalc extends SamLocusIterator {
    public SamLocusIteratorCalc(final SamReader samReader) {
        super(samReader);
    }

    @Override
    public LocusInfo next() {
        return super.next();
    }


}
