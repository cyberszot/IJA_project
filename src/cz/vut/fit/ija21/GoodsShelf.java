/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *
 * file: GoodsShelf.java
 */

package cz.vut.fit.ija21;

public interface GoodsShelf {
    void put(GoodsItem var1);

    boolean containsGoods(Goods var1);

    GoodsItem removeAny(Goods var1);

    int size(Goods var1);
}

