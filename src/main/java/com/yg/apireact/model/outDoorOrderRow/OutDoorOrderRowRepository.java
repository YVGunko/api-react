package com.yg.apireact.model.outDoorOrderRow;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OutDoorOrderRowRepository extends JpaRepository<OutDoorOrderRow,String>{

	OutDoorOrderRow findOneById(@Param(value = "id") String id);
	Optional<List<OutDoorOrderRow>> findByOutDoorOrderIdOrderByCreatedAtDesc(@Param(value = "order_id") String order_id);
	Optional<List<OutDoorOrderRow>> findByOutDoorOrderIdOrderByDtDesc(@Param(value = "order_id") String order_id);
	List<OutDoorOrderRow> findByOutDoorOrderIdOrderByDtAsc(@Param(value = "order_id") String order_id);
	List<OutDoorOrderRow> findByOutDoorOrderIdIn(@Param(value = "order_id") List<String> order_id);
	Optional<List<OutDoorOrderRow>> findByOutDoorOrderId(String orderId);
}
