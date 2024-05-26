package pe.mundoliterario.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import pe.mundoliterario.entity.Sale;
@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
	
	@Query(value="SELECT * FROM sale where customer_id =:customer_id", nativeQuery = true)
	Collection<Sale> listSalesByCustomer(Integer customer_id );
	
	@Query(value="SELECT * FROM venta where sale_status = 'Pendiente'", nativeQuery = true)
	Collection<Sale> listPendingSales();
	
	/*
	@Query(value="Select * FROM venta where fecha_venta between '2023-08-05' and '2023-08-07'", nativeQuery = true)
	Collection<Sale> obtenerVentasFecha(@Param("cliente_id")Integer cliente_id );
	*/

}
