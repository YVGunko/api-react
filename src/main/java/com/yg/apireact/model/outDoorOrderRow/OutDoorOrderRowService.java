package com.yg.apireact.model.outDoorOrderRow;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.apireact.model.outDoorOrder.OutDoorOrder;
import com.yg.apireact.model.outDoorOrder.OutDoorOrderRepository;

@Service
public class OutDoorOrderRowService {
	private static final Logger log = LoggerFactory.getLogger(OutDoorOrderRowService.class);

	@Autowired
	OutDoorOrderRowRepository repository;

	@Autowired
	OutDoorOrderRepository orderRepository;

	public String getGoods(String order_id, int length) {
		List<OutDoorOrderRow> rows = new ArrayList<>();
		String result = "";
		rows = repository.findByOutDoorOrderIdOrderByDtAsc(order_id);
		if (rows != null) {
			for (OutDoorOrderRow b : rows) {
				if (!result.equals(""))
					result = (result + ", ").trim();
				result = (result + b.getProduct().getName()).trim() + " р." + b.getSize().trim();
				if (result.length() >= length) {
					result = result + "...";
					break;
				}
			}
		}
		return result;
	}

	public OutDoorOrderRowReq saveOrUpdate(OutDoorOrderRowReq request) throws Exception {

		final OutDoorOrder outDoorOrder = orderRepository.findById(request.getOrder_id()).orElseThrow();
		final String id = StringUtils.isNotBlank(request.getId()) ? request.getId() : UUID.randomUUID().toString();
		final OutDoorOrderRow tmp = repository.save(new OutDoorOrderRow(id,
				new OutDoorOrder(outDoorOrder.getId(), outDoorOrder.getComment(), outDoorOrder.getDate(),
						outDoorOrder.getDivision(), outDoorOrder.getUser(), outDoorOrder.getCustomer(),
						outDoorOrder.getSample()),
				request.getAttribute(),
				request.getNumber(),
				request.getBarcode(),
				request.getProduct_id(), 
				request.getSize(),
				request.getColor_id(),
				request.getLiner_id(),
				request.getRant_id(),
				request.getShpalt_id(),
				request.getVstavka_id(),
				request.getGelenok_id(),
				request.getGuba_id(),
				request.getKabluk_id(),
				request.getMatirovka_id(),
				request.getPechat_id(),
				request.getProshiv_id(),
				request.getPyatka_id(),
				request.getSled_id(),
				request.getSpoyler_id(),
				request.getAshpalt_id(),
				request.getProdir(),
				request.getDifersize(),
				request.getTert(),
				request.getFrez(),
				request.getSample(),
				request.getPlastizol_id()));

		return OutDoorOrderRowReq.rowToRowReq(tmp);
	}
}
