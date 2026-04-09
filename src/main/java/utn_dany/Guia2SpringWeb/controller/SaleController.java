package utn_dany.Guia2SpringWeb.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn_dany.Guia2SpringWeb.dto.CreateSaleRequest;
import utn_dany.Guia2SpringWeb.dto.UpdateSaleRequest;
import utn_dany.Guia2SpringWeb.model.SaleEntity;
import utn_dany.Guia2SpringWeb.model.UserEntity;
import utn_dany.Guia2SpringWeb.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleEntity>> findAll () {
        List<SaleEntity> sales = saleService.findAll();
        if (sales.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(sales);
    }

    @PostMapping
    public ResponseEntity<SaleEntity>newSale(@RequestBody CreateSaleRequest dtoSale){
        SaleEntity newSale = saleService.newSale(dtoSale.getProductId(),dtoSale.getUserId(), dtoSale.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(newSale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleEntity> updateSale (@PathVariable Long id,
                                                  @RequestBody UpdateSaleRequest dtoRequest) {
        SaleEntity sale = saleService.updateSale(id, dtoRequest.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }


}
