/*
 * Created by zeeroiq on 9/19/20, 1:29 AM
 */

package com.shri.inventory.services;

import com.shri.events.NewInventoryEvent;
import com.shri.inventory.config.JmsConfig;
import com.shri.inventory.domain.BeerInventory;
import com.shri.inventory.repository.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewInventoryListener {

    private final BeerInventoryRepository repository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {
        log.debug(">>>>> Got inventory : " + event.toString());
        repository.save(BeerInventory
                .builder()
                .beerId(event.getBeerDto().getId())
                .upc(event.getBeerDto().getUpc().toString())
                .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                .build());
    }
}
