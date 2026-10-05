package restaurant.infrastructure.repository;

import restaurant.domain.entity.Discount;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public abstract class Repository <T,D>{
    List<T> list;
    Repository(List<T> list)
    {
        this.list = list;
    }
    // Thêm item vào list chứa các T
    public void add(T item)
    {
        list.add(item);
    }
    // tìm item thông qua id của nó
    public T findById(D id, Function<T,D> getId)
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
        List<T> result = new ArrayList<>();
        for (var item : list)
        {
            if(condition.test(item))
            {
                result.add(item);
            }
        }
        return result;
    }
}
