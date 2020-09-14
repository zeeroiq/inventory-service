/*
 *  Created by zeeroiq on 9/15/20, 12:10 AM.
 */

package com.shri.inventory.mappers;

import com.shri.inventory.domain.BeerInventory;
import com.shri.inventory.model.BeerInventoryDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerInventoryMapper {
    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDto);
    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
