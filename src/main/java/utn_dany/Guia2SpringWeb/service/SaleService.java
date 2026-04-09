package utn_dany.Guia2SpringWeb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utn_dany.Guia2SpringWeb.exceptions.*;
import utn_dany.Guia2SpringWeb.model.ProductEntity;
import utn_dany.Guia2SpringWeb.model.SaleEntity;
import utn_dany.Guia2SpringWeb.model.UserEntity;
import utn_dany.Guia2SpringWeb.repository.ProductRepository;
import utn_dany.Guia2SpringWeb.repository.SaleRepository;
import utn_dany.Guia2SpringWeb.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private  final UserRepository userRepository;

    public List<SaleEntity> findAll() {
        return saleRepository.findAll();
    }

    public SaleEntity newSale(long productId, long userId, int quantity) {

        //Validaciones
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow( ()->new NullProductException("product doen´t exist"));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NullUserException("user doesn´t exist"));

        if(quantity<0){
            throw new IllegalQuantityException("No negative");
        }
        if(quantity>product.getStock()){
            throw new InsuficientStockException("Insuficient stock");
        }

        SaleEntity sale = new SaleEntity();
            sale.setProductId(productId);
            sale.setUserId(userId);
            sale.setTotalPrice(product.getPrice()*quantity);

            product.setStock(product.getStock()-quantity);
            productRepository.update(product);
        return sale;
    }

    public SaleEntity updateSale (Long id,int newQuantity) {

        if(newQuantity<0){
            throw new IllegalQuantityException("bad quantity");
        }
        SaleEntity saleToUpdate = saleRepository.findById(id)
                .orElseThrow( ()->new NullSaleException("sale doesn´t exist"));
        ProductEntity product = productRepository.findById(saleToUpdate.getProductId())
                .orElseThrow( ()->new NullProductException("product doesn´t exist") );
        if(newQuantity>product.getStock()){
            throw new InsuficientStockException("Insuficient stock");
        }
        saleToUpdate.setQuantity(newQuantity);
        return saleRepository.update(saleToUpdate);
    }

    public void deleteSale (Long id) {

        SaleEntity saleToDelete = saleRepository.findById(id)
                .orElseThrow(() -> new NullSaleException("sale doesn't exist"));
    }

}
