/*
 * Created by zeeroiq on 9/25/20, 11:44 PM
 */

package com.shri.inventory.services;

import com.shri.inventory.config.JmsConfig;
import com.shri.model.events.AllocateOrderRequest;
import com.shri.model.events.AllocateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest request) {
        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();
        builder.beerOrderDto(request.getBeerOrderDto());

        try {
            Boolean allocateOrderResult = allocationService.allocateOrder(request.getBeerOrderDto());
            builder.pendingInventory(allocateOrderResult ? false : true);
            builder.allocationError(false);
        }
        catch (Exception e) {
            log.error(">>>>> Allocation failed for orderId: " + request.getBeerOrderDto().getId());
            builder.allocationError(true);
        }

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());
    }
}
