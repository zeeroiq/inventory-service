/*
 * Created by zeeroiq on 9/19/20, 1:23 AM
 */

package com.shri.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@RequiredArgsConstructor
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = 9184054766017896048L;

    private final BeerDto beerDto;
}
