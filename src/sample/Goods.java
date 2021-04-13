/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *
 * file: Goods.java
 */

package sample;

import java.time.LocalDate;

public interface Goods {
    String getName();

    boolean addItem(GoodsItem var1);

    GoodsItem newItem(LocalDate var1);

    boolean remove(GoodsItem var1);

    boolean empty();

    int size();
}
