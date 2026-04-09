package utn_dany.Guia2SpringWeb.exceptions;

public class InsuficientStockException extends RuntimeException{
    public InsuficientStockException(String message) {
        super(message);
    }
}
