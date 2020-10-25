/*
 * Created by zeeroiq on 9/24/20, 2:58 AM
 */

package com.shri.inventory.services;

import com.shri.inventory.domain.BeerInventory;
import com.shri.inventory.repository.BeerInventoryRepository;
import com.shri.model.BeerOrderDto;
import com.shri.model.BeerOrderLineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class AllocationServiceImpl implements AllocationService {

    private final BeerInventoryRepository inventoryRepository;

    @Override
    public Boolean allocateOrder(BeerOrderDto beerOrderDto) {

        log.debug(">>>>> Allocating orderId: " + beerOrderDto.getId());
        AtomicInteger ordered = new AtomicInteger();
        AtomicInteger allocated = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(beerOrderLine -> {
            if (((beerOrderLine.getOrderQuantity() != null ? beerOrderLine.getOrderQuantity() : 0)
                    -
                    (beerOrderLine.getQuantityAllocated() != null ? beerOrderLine.getQuantityAllocated() : 0)) > 0) {
                allocateBeerOrderLine(beerOrderLine);
            }
            ordered.set(ordered.get() + beerOrderLine.getOrderQuantity());
            allocated.set(allocated.get() + (beerOrderLine.getQuantityAllocated() != null ? beerOrderLine.getQuantityAllocated() : 0));
        });

        log.debug(">>>>> Total ordered: " + ordered.get());
        log.debug(">>>>> Total Allocated: " + allocated.get());
        return ordered.get() == allocated.get();
    }

    @Override
    public void deallocateOrder(BeerOrderDto beerOrderDto) {
        beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {
            BeerInventory inventory = BeerInventory.builder()
                    .beerId(beerOrderLineDto.getId())
                    .upc(beerOrderLineDto.getUpc())
                    .quantityOnHand(beerOrderLineDto.getQuantityAllocated())
                    .build();
            BeerInventory savedInventory = inventoryRepository.save(inventory);

            log.debug(">>>>> Saved inventory for beer UPC: " + savedInventory.getUpc()
                    + " inventory id: " + savedInventory.getId());
        });
    }

    private void allocateBeerOrderLine(BeerOrderLineDto beerOrderLine) {
        List<BeerInventory> allInventoryByUpc = inventoryRepository.findAllByUpc(beerOrderLine.getUpc());

        allInventoryByUpc.forEach(beerInventory -> {
            int inventory = beerInventory.getQuantityOnHand() == null ? 0 : beerInventory.getQuantityOnHand();
            int orderedQuantity = beerOrderLine.getOrderQuantity() == null ? 0 : beerOrderLine.getOrderQuantity();
            int allocatedQuantity = beerOrderLine.getQuantityAllocated() == null ? 0 : beerOrderLine.getQuantityAllocated();
            int quantityToAllocate = orderedQuantity - allocatedQuantity;

            // full allocation
            if(inventory >= quantityToAllocate) {
                inventory -= quantityToAllocate;
                beerOrderLine.setQuantityAllocated(orderedQuantity);
                beerInventory.setQuantityOnHand(inventory);

                inventoryRepository.save(beerInventory);
            }
            // partial allocation
            else if (inventory > 0){
                beerOrderLine.setQuantityAllocated(allocatedQuantity + inventory);
                beerInventory.setQuantityOnHand(0);
            }

            if(beerInventory.getQuantityOnHand() == 0) {
                inventoryRepository.delete(beerInventory);
            }
        });
    }
}
