package com.yg.apireact.model.outDoorOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface OutDoorOrderRepository extends PagingAndSortingRepository<OutDoorOrder, String>{
	@Query ("SELECT count(id) FROM OutDoorOrder where client.id=?1")
	Long getNextOrderNumber(String id);

	Page<OutDoorOrder> findAllByOrderByDateDesc(Pageable paging);

	Page<OutDoorOrder> findByClientIdOrderByDateDesc(String customerId, Pageable paging);

	Page<OutDoorOrder> findByUserIdOrderByDateDesc(String userId, Pageable paging);

	Page<OutDoorOrder> findByClientIdAndUserIdOrderByDateDesc(String customerId, String userId, Pageable paging);
	
	Optional<List<OutDoorOrder>> findByUserIdAndDateBetweenOrderByDateDesc(Long userId, Date from, Date till);
	
	Optional<List<OutDoorOrder>> findByDateBetweenOrderByDateDesc(Date from, Date till);
	
	@Query ("SELECT new com.yg.apireact.model.outDoorOrder.OutDoorOrder (id, comment, date, division.code, user.id, client.id, sample) \n"+ 
    		" FROM OutDoorOrder \n"
    		+ " WHERE date between :d1 and :d2 "
    		+ " and (:user IS NULL or user.id=:user)  \n"
    		+ " and (:filial IS NULL or comment=:filial)  \n"
    		+ " and (:division IS NULL or division.code=:division)  \n"
    		+ " and (:customer IS NULL or client.id=:customer)  \n"
    		+ " ORDER BY date desc  \n")
	List<OutDoorOrder> find(@Param("d1") Date from, @Param("d2") Date till, 
			@Param("user") Long user, 
			@Param("filial") String filial, 
			@Param("division") String division,
			@Param("customer") String customer);

	@Query(value="select sum(number) from out_door_order_row \n"+
			" where out_door_order_id = ?1", nativeQuery=true)
	Long getOrderTotal(String id);
	
}
/*
 * 
 * 	@Query ("SELECT new com.yg.apireact.model.outDoorOrder.OutDoorOrder (id, comment, date, receivedFromMobileDate, sentToMasterDate, division, user, customer) \n"+ 
    		" FROM OutDoorOrder \n" + 
    		" WHERE date between :d1 and :d2 \n"+
    		" and (:filial IS NULL or comment=:filial)  \n"+
    		" and (:division IS NULL or division.code=:division)  \n"+
    		" and (:user IS NULL or user.id=:user)  \n"+
    		" and (:customer IS NULL or customer.id=:customer)  ")
	List<OutDoorOrder> find(
			@Param("d1") Date from, @Param("d2") Date till, 
			@Param("filial") String filial, 
			@Param("division") String division,
			@Param("user") Long user,
			@Param("customer") String customer);*/
