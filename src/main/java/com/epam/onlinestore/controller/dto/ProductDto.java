package com.epam.onlinestore.controller.dto;

import com.epam.onlinestore.controller.validation.ValidDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * DTO class for fast serialization and transfer to the Internet
 */
@Slf4j
@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @JsonProperty(access = READ_ONLY)
    public String id;

    @NotEmpty(message = "{login.notempty}")
    public String name;

    @ValidDescription
    public String description;

    @Positive
    public double price;

    @Positive
    public int quantity;

}
