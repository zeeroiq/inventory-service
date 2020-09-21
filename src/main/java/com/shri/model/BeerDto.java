/*
 * Created by zeeroiq on 9/20/20, 1:58 AM
 */

/*
 * Created by zeeroiq on 9/19/20, 1:15 AM
 */

package com.shri.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerDto implements Serializable {

    private static final long serialVersionUID = -8814795183633450726L;

    @Null
    private UUID id;
    @Null
    private Integer version;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdOn;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime modifiedOn;
    @NotBlank
    private String beerName;
    @NotNull
    private String beerStyle;
    @Positive
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long upc;
    @Positive
    @NotNull
    private BigDecimal price;
    private Integer quantityOnHand;
}
