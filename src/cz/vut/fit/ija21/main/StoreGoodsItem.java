/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *
 * file: StoreGoodsItem.java
 */

package cz.vut.fit.ija21.main;

import java.time.LocalDate;


public class StoreGoodsItem implements GoodsItem{

    private Goods _goods;
    private LocalDate localDate;

    public StoreGoodsItem(Goods goods, LocalDate localDate) {
        this._goods = goods;
        this.localDate = localDate;
    }

    @Override
    public Goods goods() {
        return this._goods;
    }

    @Override
    public boolean sell() {
        return this._goods.remove(this);
    }
}
