package com.IMSApi.Inventory.Management.System.API.Repository;

import com.IMSApi.Inventory.Management.System.API.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Products, UUID> {

    @Query(value = "select * from products where stock_quantity<low_stock_threshold",nativeQuery = true)
    public List<Products> findStockQuantityLessThan();
}
