/**
 * project IJA21
 * date: 2021/04/13
 * authors: xkyjov03    :   Dalibor Kyjovsky
 *
 * file: StoreGoods.java
 */

package cz.vut.fit.ija21.main;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;


public class StoreGoods implements Goods {

    private String name;
    private HashSet<GoodsItem> items;


    public StoreGoods(String name) {
        this.name = name;
        this.items = new HashSet<GoodsItem>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreGoods goods = (StoreGoods) o;
        return Objects.equals(name, goods.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean addItem(GoodsItem goodsItem) {
        return this.items.add(goodsItem);
    }

    @Override
    public GoodsItem newItem(LocalDate localDate) {
        StoreGoodsItem item = new StoreGoodsItem(this, localDate);
        this.addItem(item);
        return item;
    }

    @Override
    public boolean remove(GoodsItem goodsItem) {
        return this.items.remove(goodsItem);
    }

    @Override
    public boolean empty() {
        return this.items.isEmpty();
    }

    @Override
    public int size() {
        return this.items.size();
    }
}
