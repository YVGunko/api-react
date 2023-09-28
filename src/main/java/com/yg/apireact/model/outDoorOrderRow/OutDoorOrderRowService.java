package com.yg.apireact.model.outDoorOrderRow;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

		OutDoorOrder outDoorOrder = orderRepository.findById(request.getOrder_id()).orElseThrow();

		OutDoorOrderRow tmp = repository.save(new OutDoorOrderRow(request.getId(),
				new OutDoorOrder(outDoorOrder.getId(), outDoorOrder.getComment(), outDoorOrder.getDate(),
						outDoorOrder.getDivision(), outDoorOrder.getUser(), outDoorOrder.getCustomer(),
						outDoorOrder.getSample()),
				(!(request.getAttribute() == null)) ? request.getAttribute() : "",
				(!(request.getNumber() == null)) ? request.getNumber() : 0,
				(!(request.getBarcode() == null)) ? request.getBarcode() : "",
				(!(request.getProduct_id() == null)) ? request.getProduct_id() : "0", request.getSize(),
				(!(request.getColor_id() == null)) ? request.getColor_id() : "0",
				(!(request.getLiner_id() == null)) ? request.getLiner_id() : "0",
				(!(request.getRant_id() == null)) ? request.getRant_id() : "0",
				(!(request.getShpalt_id() == null)) ? request.getShpalt_id() : "0",

				(!(request.getVstavka_id() == null)) ? request.getVstavka_id() : "0",
				(!(request.getGelenok_id() == null)) ? request.getGelenok_id() : "0",
				(!(request.getGuba_id() == null)) ? request.getGuba_id() : "0",
				(!(request.getKabluk_id() == null)) ? request.getKabluk_id() : "0",
				(!(request.getMatirovka_id() == null)) ? request.getMatirovka_id() : "0",
				(!(request.getPechat_id() == null)) ? request.getPechat_id() : "0",
				(!(request.getProshiv_id() == null)) ? request.getProshiv_id() : "0",

				(!(request.getPyatka_id() == null)) ? request.getPyatka_id() : "0",
				(!(request.getSled_id() == null)) ? request.getSled_id() : "0",
				(!(request.getSpoyler_id() == null)) ? request.getSpoyler_id() : "0",
				(!(request.getAshpalt_id() == null)) ? request.getAshpalt_id() : "0",
				(!(request.getProdir() == null)) ? request.getProdir() : false,
				(!(request.getDifersize() == null)) ? request.getDifersize() : false,
				(!(request.getTert() == null)) ? request.getTert() : false,
				(!(request.getFrez() == null)) ? request.getFrez() : false,
				(!(request.getSample() == null)) ? request.getSample() : false,
				(!(request.getPlastizol_id() == null)) ? request.getPlastizol_id() : "0"));

		return OutDoorOrderRowReq.rowToRowReq(tmp);
	}
}
