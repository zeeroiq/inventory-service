/*
 *  Created by zeeroiq on 9/15/20, 12:49 AM.
 */

package com.shri.inventory.controller;

import com.shri.inventory.mappers.BeerInventoryMapper;
import com.shri.inventory.model.BeerInventoryDto;
import com.shri.inventory.repository.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class BeerInventoryController {

    private final BeerInventoryRepository inventoryRepository;
    private final BeerInventoryMapper inventoryMapper;

    @GetMapping("beer/{beerId}/inventory")
    public List<BeerInventoryDto> listBeerById(@PathVariable("beerId") UUID beerId) {
        log.debug(">>>>> Finding Inventory for beerId:" + beerId);
        return inventoryRepository.findAllByBeerId(beerId)
                .stream()
                .map(inventoryMapper::beerInventoryToBeerInventoryDto)
                .collect(Collectors.toList());
    }
}
