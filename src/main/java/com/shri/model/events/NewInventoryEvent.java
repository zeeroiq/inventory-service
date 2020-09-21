/*
 * Created by zeeroiq on 9/19/20, 1:25 AM
 */

package com.shri.model.events;

import com.shri.model.BeerDto;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent implements Serializable {

    private static final long serialVersionUID = 8047582795321225060L;

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
