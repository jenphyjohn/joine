package com.github.joine.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: JenphyJohn
 * @Date: 2019/6/7 12:43 PM
 */
public class ListUtils {

    /**
     * 比较2个List，得出应该删除的 delList，和新增的addList T必须重写 hashCode和equals方法
     *
     * @param oldList   旧集
     * @param newList   新集
     * @param addList   增集
     * @param delList   删集
     */
    public static  <T> void filterList(List<T> oldList, List<T> newList, List<T> addList, List<T> delList) {
        if (oldList == null) {
            oldList = new ArrayList<>();
        }
        if (newList == null) {
            newList = new ArrayList<>();
        }
        // 复制一个原始数组,将作为交集
        List<T> intersecList = new ArrayList<>(newList);
        // 设新集A(newList)，旧集 B(oldList)，求出交集C(intersecList)
        intersecList.retainAll(oldList);
        // A -C ,即addList
        newList.removeAll(intersecList);
        addList.addAll(newList);
        // B -C,即delList
        oldList.removeAll(intersecList);
        delList.addAll(oldList);
    }

}
