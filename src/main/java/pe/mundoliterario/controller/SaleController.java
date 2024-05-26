package pe.mundoliterario.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.crisol.util.MapperCrisol;
import pe.mundoliterario.entity.Sale;
import pe.mundoliterario.entity.SaleDetails;
import pe.mundoliterario.service.SaleDetailsService;
import pe.mundoliterario.service.SalesService;
import pe.mundoliterario.util.MapperMundoLiterario;
import pe.mundoliterario.vo.SaleDetailDto;

@RestController

public class SaleController {
	@Autowired
	SalesService saleService;
	
	@Autowired
	SaleDetailsService detailsService;

	public SaleController() {
	}
	
	/*
	@PostMapping("/sale/saves")
	public ResponseEntity<?> saveSales(@RequestBody Sale sale){
		
		saleService.save(sale);
		
		Map<String, String> response = new HashMap<>();

		response.put("message", "Venta registrada correctamente.");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
		
	}
	
	@PostMapping("/sale/dsaves")
	public ResponseEntity<?> dsaveSales(@RequestBody SaleDetails details){
		
		detailsService.save(details);
		
		Map<String, String> response = new HashMap<>();

		response.put("message", "Venta registrada correctamente.");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
		
	}
	*/
	
	
	
	
    @PostMapping("/sale/save")
    @Transactional
    public ResponseEntity<?> saveSale(@RequestBody SaleDetailDto dto) {
        Map<String, String> response = new HashMap<>();

        try {
            // Guardar la venta
            Sale sale = dto.getSale();
            sale.setSale_status("Pendiente");
            sale = saleService.save(sale);

            // Guardar los detalles de la venta
            for (SaleDetails dsale : dto.getDetails()) {
                dsale.setSale(sale);
                detailsService.save(dsale);
            }

            response.put("message", "Venta registrada con éxito!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Manejo de excepciones
            response.put("message", "Error al registrar la venta: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    
    
    @PutMapping("/sale/confirm/{sale_id}")
    public ResponseEntity<?> confirm_sale(@PathVariable Integer sale_id) {
        Sale sale = saleService.findId(sale_id);

        Map<String, String> response = new HashMap<>();

        if (sale != null) {
            sale.setSale_status("Entregado");
            saleService.edit(sale);

            response.put("message", "Venta entregada correctamente.");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        
        response.put("message", "404 E");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    
    
    
    
    
    
	@GetMapping("/sale/details/{id}")
	public ResponseEntity<?> getDetails(@PathVariable Integer id){
		return new ResponseEntity<>(MapperMundoLiterario.toSalesD(detailsService.listDetails(id)), HttpStatus.OK);
	}
	
	
	
	@GetMapping("/sale/listPending")
	public ResponseEntity<?> getPending(){
		return new ResponseEntity<>(MapperMundoLiterario.toSales(saleService.listPending()), HttpStatus.OK);
	}
	
	@GetMapping("/sale/customer/{id}")
	public ResponseEntity<?> getCustomerPurchases(Integer id){
		return new ResponseEntity<>(MapperMundoLiterario.toSales(saleService.listForCustomer(id)), HttpStatus.OK);
	}
	
	@GetMapping("/sale/find/{id}")
	public ResponseEntity<?> findSale(Integer id){
		 Sale sale = saleService.findId(id);
		 
		 if(sale!=null) {
			 return new ResponseEntity<>(sale,HttpStatus.OK);
		 }
		 return new ResponseEntity<>("Venta no existe!",HttpStatus.NOT_FOUND);
	}
	
	
	
}
