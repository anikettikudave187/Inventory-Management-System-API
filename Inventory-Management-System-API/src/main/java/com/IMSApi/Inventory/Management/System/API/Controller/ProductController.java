package com.IMSApi.Inventory.Management.System.API.Controller;

import com.IMSApi.Inventory.Management.System.API.Exceptions.ProductNotFoundException;
import com.IMSApi.Inventory.Management.System.API.RequestBody.ProductRequestBody;
import com.IMSApi.Inventory.Management.System.API.Models.Products;
import com.IMSApi.Inventory.Management.System.API.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ims/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Products> createProduct(@RequestBody ProductRequestBody productRequestBody){
        Products product=productService.createProduct(productRequestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Products> findProductById(@PathVariable UUID productId){
            Products products= productService.findProductById(productId);
            if(products==null){
                throw new ProductNotFoundException("Product Not found id Is not valid");
            }
            return ResponseEntity.ok(products);
    }


    @PutMapping("/{productId}")
    public ResponseEntity<Products> updateProductById(@PathVariable UUID productId,@RequestBody ProductRequestBody productRequestBody) {
        Products product = productService.updateProduct(productId, productRequestBody);
        if (product == null) {
            throw new ProductNotFoundException("Not found");
        }
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable UUID productId){
        String res=productService.deleteProductById(productId);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{productId}/increaseQuantity")
    public ResponseEntity<Products> increaseProductQuantity(@PathVariable UUID productId,@RequestParam int amount){
        Products products=productService.increaseStockQuantity(productId,amount);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{productId}/decreaseQuantity")
    public ResponseEntity<Products> decreaseProductQuantity(@PathVariable UUID productId,@RequestParam int amount){
        Products product=productService.decreaseStockQuantity(productId,amount);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Products>> getAllBelowThreshHold(){
        List<Products> allProducts=productService.getAllProductsBelowTheThreshHold();
        return ResponseEntity.ok(allProducts);
    }
}
