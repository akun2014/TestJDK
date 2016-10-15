package com.gk.guava.collectionUtils;

import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by akun on 2016/10/16.
 */
public class TestCollectionUtils {


    public void testUtils() {
        Collection<List<Comparable>> lists = Collections2.orderedPermutations(null);
        Lists.newArrayList(1,2,2);
        Maps.newHashMap();
        Sets.newHashSet();
        Queues.newArrayDeque();
    }
}
