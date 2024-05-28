package pe.mundoliterario.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import pe.mundoliterario.entity.Sale;
import pe.mundoliterario.entity.SaleDetails;

public class SaleDetailDto {

    private Sale sale;
    private List<SaleDetails> details;

    public SaleDetailDto() {}

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public List<SaleDetails> getDetails() {
        return details;
    }

    public void setDetails(List<SaleDetails> details) {
        this.details = details;
    }
		
}
