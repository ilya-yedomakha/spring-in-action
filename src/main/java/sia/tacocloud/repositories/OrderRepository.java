package sia.tacocloud.repositories;

import sia.tacocloud.domain.Order;

public interface OrderRepository {

    Order save(Order order);

}

