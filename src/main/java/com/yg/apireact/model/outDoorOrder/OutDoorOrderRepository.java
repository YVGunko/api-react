package com.yg.apireact.model.outDoorOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface OutDoorOrderRepository extends PagingAndSortingRepository<OutDoorOrder, String>{
	@Query ("SELECT count(id) FROM OutDoorOrder where client.id=?1")
	Long getNextOrderNumber(String id);

	Page<OutDoorOrder> findAllByOrderByDateDesc(Pageable paging);

	Page<OutDoorOrder> findByClientIdOrderByDateDesc(String customerId, Pageable paging);

	Page<OutDoorOrder> findByUserIdOrderByDateDesc(String userId, Pageable paging);

	Page<OutDoorOrder> findByClientIdAndUserIdOrderByDateDesc(String customerId, String userId, Pageable paging);
}