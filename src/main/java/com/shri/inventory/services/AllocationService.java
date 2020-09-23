/*
 * Created by zeeroiq on 9/24/20, 2:57 AM
 */

package com.shri.inventory.services;

import com.shri.model.BeerOrderDto;

public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);
}
