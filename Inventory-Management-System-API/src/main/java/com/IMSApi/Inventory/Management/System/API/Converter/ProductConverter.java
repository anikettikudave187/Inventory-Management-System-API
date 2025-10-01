package com.IMSApi.Inventory.Management.System.API.Converter;

import com.IMSApi.Inventory.Management.System.API.RequestBody.ProductRequestBody;
import com.IMSApi.Inventory.Management.System.API.Models.Products;

public class ProductConverter {

    public static Products convertProductRequestBodyToProduct(ProductRequestBody productRequestBody){
        Products product=new Products();

        product.setDescription(productRequestBody.getDescription());
        product.setName(productRequestBody.getName());
        product.setStock_quantity(productRequestBody.getStock_quantity());
        product.setLow_stock_threshold(productRequestBody.getLow_stock_threshold());


       return product;
    }
}
