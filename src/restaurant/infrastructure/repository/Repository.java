package restaurant.infrastructure.repository;

import restaurant.domain.entity.Discount;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
// this repo has method: add, findById*, findAll, findBy*
public abstract class Repository <T,DT>{
    protected final List<T> list;
    public Repository(List<T> list)
    {
        this.list = list;
    }
    // Thêm item vào list chứa các T
    public void add(T item)
    {
        list.add(item);
    }
    // tìm item thông qua id của nó
    public T findById(DT id, Function<T,DT> getId)
    {
        for (var item : list) {
            if (getId.apply(item).equals(id)) {
                return item;
            }
        }
        return null;
    }
    // trả ra copy list<T>
    public List<T> findAll() {
        return new ArrayList<>(list);
    }
    // trả ra list các item thỏa mãn condition
    public List<T> findBy(Predicate<T> condition)
    {
        return list.stream().filter(condition).toList();
    }
}
