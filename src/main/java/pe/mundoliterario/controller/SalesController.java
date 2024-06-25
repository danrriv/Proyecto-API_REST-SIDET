package pe.mundoliterario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.mundoliterario.entity.Sale;
import pe.mundoliterario.entity.SaleDetails;
import pe.mundoliterario.service.SaleDetailsService;
import pe.mundoliterario.service.SalesService;
import pe.mundoliterario.util.MapperMundoLiterario;
import pe.mundoliterario.vo.SaleDetailDto;
@RestController
@RequestMapping("/sale")
@CrossOrigin(origins = "http://localhost:4200")
public class SalesController {
	
	@Autowired
	SalesService saleService;
	
	@Autowired
	SaleDetailsService detailsService;
	
	public SalesController() {
		// TODO Auto-generated constructor stub
	}
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity<?> createSale(@RequestBody SaleDetailDto saleDetailDto) {
        Sale sale = saleDetailDto.getSale();
        List<SaleDetails> saleDetailsList = saleDetailDto.getDetails();

        for (SaleDetails saleDetails : saleDetailsList) {
            saleDetails.setSale(sale);
        }

        sale.setItemSalesDetail(saleDetailsList);
        sale.setSale_status("Pendiente");

        saleService.save(sale);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Venta registrada correctamente.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/confirm/{sale_id}")
    public ResponseEntity<?> confirm_sale(@PathVariable Integer sale_id) {
        Sale sale = saleService.findId(sale_id);

        Map<String, String> response = new HashMap<>();

        if (sale != null) {
            sale.setSale_status("Entregado");
            saleService.update(sale);

            response.put("message", "Venta entregada correctamente.");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }     
        response.put("message", "404 E");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    
	@GetMapping("/details/{id}")
	public ResponseEntity<?> getDetails(@PathVariable Integer id){
		return new ResponseEntity<>(MapperMundoLiterario.toSalesD(detailsService.listDetails(id)), HttpStatus.OK);
	}
	
	
	@GetMapping("/listPending")
	public ResponseEntity<?> getPending(){
		return new ResponseEntity<>(MapperMundoLiterario.toSales(saleService.listPending()), HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(MapperMundoLiterario.toSales(saleService.listAll()), HttpStatus.OK);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomerPurchases(@PathVariable Integer id){
		return new ResponseEntity<>(MapperMundoLiterario.toSales(saleService.listForCustomer(id)), HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findSale(@PathVariable Integer id){
		 Sale sale = saleService.findId(id);
		 
		 if(sale!=null) {
			 return new ResponseEntity<>(sale,HttpStatus.OK);
		 }
		 return new ResponseEntity<>("Venta no existe!",HttpStatus.NOT_FOUND);
	}
	
	
	

}
