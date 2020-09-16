/*
 *  Created by zeeroiq on 9/15/20, 12:12 AM.
 */

package com.shri.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerInventoryDto implements Serializable {

    private static final long serialVersionUID = -8434950402574501525L;

    private UUID id;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private UUID beerId;
    private String upc;
    private Integer quantityOnHand;

}
