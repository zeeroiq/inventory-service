/*
 *  Created by zeeroiq on 9/15/20, 12:48 AM.
 */

package com.shri.inventory.repository;


import com.shri.inventory.domain.BeerInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerInventoryRepository extends JpaRepository<BeerInventory, UUID> {
    List<BeerInventory> findAllByBeerId(UUID beerId);

    List<BeerInventory> findAllByUpc(String upc);
}
