package pe.mundoliterario.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.mundoliterario.entity.SaleDetails;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Integer> {
	
	@Query(value="SELECT * FROM saleDetails WHERE sale_id =: sale_id",nativeQuery = true)
	Collection<SaleDetails> obtainSaleDetails(Integer sale_id);

}
