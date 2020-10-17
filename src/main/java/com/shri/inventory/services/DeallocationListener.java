/*
 * Created by zeeroiq on 9/19/20, 1:29 AM
 */

package com.shri.inventory.services;

import com.shri.inventory.config.JmsConfig;
import com.shri.inventory.domain.BeerInventory;
import com.shri.inventory.repository.BeerInventoryRepository;
import com.shri.model.events.DeallocateOrderRequest;
import com.shri.model.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DeallocationListener {

    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest request) {
        log.debug(">>>>> De-allocating request order for inventory orderId: " + request.getBeerOrderDto().getId());
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}
