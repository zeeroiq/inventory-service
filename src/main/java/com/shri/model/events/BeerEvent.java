/*
 * Created by zeeroiq on 9/19/20, 1:23 AM
 */

package com.shri.model.events;

import com.shri.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = 9184054766017896048L;

    private BeerDto beerDto;
}
