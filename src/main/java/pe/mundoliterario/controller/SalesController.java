package pe.mundoliterario.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.mundoliterario.entity.Sale;
import pe.mundoliterario.entity.SaleDetails;
import pe.mundoliterario.service.SaleDetailsService;
import pe.mundoliterario.service.SalesService;
import pe.mundoliterario.vo.SaleDetailDto;

@RestController
@RequestMapping("/sale")
public class SalesController {

	@Autowired
	SalesService saleservice;
	
	@Autowired
	SaleDetailsService sdservice;
	
	
    @PostMapping("/save")
    public ResponseEntity<?> registrarVenta(@RequestBody SaleDetailDto dto){
    	
    	Map<String, String> response = new HashMap<>();
    	
    	Sale newSale = saleservice.create(dto.getSale());
    	
    	newSale.setSale_status("Pendiente");
   	
    	for(SaleDetails details: dto.getDetails() ) {
    		details.setSale(newSale);
    		sdservice.create(details);
    	}
    	
    	response.put("message", "Venta registrada con éxito!");
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);	
    }
}
