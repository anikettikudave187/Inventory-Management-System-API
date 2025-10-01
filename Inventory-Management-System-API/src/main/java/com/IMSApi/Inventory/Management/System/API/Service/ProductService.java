package com.IMSApi.Inventory.Management.System.API.Service;

import com.IMSApi.Inventory.Management.System.API.Converter.ProductConverter;
import com.IMSApi.Inventory.Management.System.API.Exceptions.InvalidStockException;
import com.IMSApi.Inventory.Management.System.API.Exceptions.ProductNotFoundException;
import com.IMSApi.Inventory.Management.System.API.RequestBody.ProductRequestBody;
import com.IMSApi.Inventory.Management.System.API.Models.Products;
import com.IMSApi.Inventory.Management.System.API.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;


    public Products createProduct(ProductRequestBody productRequestBody){
        Products product= ProductConverter.convertProductRequestBodyToProduct(productRequestBody);

        if(product.getStock_quantity()<0){
            product.setStock_quantity(0);
        }
        productRepo.save(product);

        //return "Product Saved Successfully !";
        return product;
    }

    public Products findProductById(UUID id){
        Products product=productRepo.findById(id).orElse(null);
        return product;
    }

    public Products updateProduct(UUID id,ProductRequestBody productRequestBody){
        Products product=findProductById(id);

        if(product==null){
            return product;
        }else{
            product.setName(productRequestBody.getName());
            product.setDescription(productRequestBody.getDescription());
            product.setLow_stock_threshold(productRequestBody.getLow_stock_threshold());
            if(productRequestBody.getStock_quantity()>=0){
                product.setStock_quantity(productRequestBody.getStock_quantity());
            }else{
                product.setStock_quantity(0);
            }
        }
        productRepo.save(product);
        return product;
    }

    public String deleteProductById(UUID id){
        if(!productRepo.existsById(id))throw new ProductNotFoundException("this product not exist");
        productRepo.deleteById(id);
        return "Product Deleted Successfully";
    }

    public Products increaseStockQuantity(UUID productId,int increaseAmount){
        if(increaseAmount<0)throw new InvalidStockException("Invalid Amount");
        Products product=findProductById(productId);
        if(product==null)throw new ProductNotFoundException("product not found");
        int stockQuantity=product.getStock_quantity();
        stockQuantity+=increaseAmount;

        product.setStock_quantity(stockQuantity);
        productRepo.save(product);
        return product;
    }

    public Products decreaseStockQuantity(UUID productId,int decreaseAmount){
        if(decreaseAmount<0)throw new InvalidStockException("Invalid Amount");
        Products product=findProductById(productId);
        if(product==null)throw new ProductNotFoundException("product not found");
        int stockQuantity=product.getStock_quantity();
        if(stockQuantity-decreaseAmount<0){
            throw new InvalidStockException("Stock is Invalid");
        }

        product.setStock_quantity(stockQuantity-decreaseAmount);
        productRepo.save(product);
        return product;
    }

    public List<Products> getAllProductsBelowTheThreshHold(){
        List<Products> allBelowThreshHold=productRepo.findStockQuantityLessThan();
        return allBelowThreshHold;
    }


}
