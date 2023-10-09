package com.yg.apireact.model.outDoorOrderRow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.apireact.Constants;
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
				request.getAttribute(), request.getNumber(), request.getBarcode(), request.getProduct_id(),
				request.getSize(), request.getColor_id(), request.getLiner_id(), request.getRant_id(),
				request.getShpalt_id(), request.getVstavka_id(), request.getGelenok_id(), request.getGuba_id(),
				request.getKabluk_id(), request.getMatirovka_id(), request.getPechat_id(), request.getProshiv_id(),
				request.getPyatka_id(), request.getSled_id(), request.getSpoyler_id(), request.getAshpalt_id(),
				request.getProdir(), request.getDifersize(), request.getTert(), request.getFrez(), request.getSample(),
				request.getPlastizol_id(), request.getCreatedAt()));

		return OutDoorOrderRowReq.rowToRowReq(tmp);
	}
	
	public OutDoorOrderRowReq copy(OutDoorOrderRow request, OutDoorOrder order) throws Exception {
		final OutDoorOrderRow tmp = repository.save(new OutDoorOrderRow(UUID.randomUUID().toString(),
				order,
				request.getAttribute(), request.getNumber(), request.getBarcode(), 
				request.getProduct().getId(),
				request.getSize(), 
				request.getColor().getId(), 
				request.getLiner().getId(),
				request.getRant().getId(), 
				request.getShpalt().getId(), 
				request.getVstavka().getId(),
				request.getGelenok().getId(), 
				request.getGuba().getId(), 
				request.getKabluk().getId(),
				request.getMatirovka().getId(), 
				request.getPechat().getId(), 
				request.getProshiv().getId(),
				request.getPyatka().getId(), 
				request.getSled().getId(), 
				request.getSpoyler().getId(),
				request.getAshpalt().getId(), 
				request.getProdir(), 
				request.getDifersize(), 
				request.getTert(),
				request.getFrez(), 
				request.getSample(), 
				request.getPlastizol().getId(),
				request.getCreatedAt()));

		return OutDoorOrderRowReq.rowToRowReq(tmp);
	}

	public void saveOrUpdate(List<OutDoorOrderRow> list) throws Exception {
		final List<String> listOfOrderId = list.stream().map(OutDoorOrderRow::getOutDoorOrder).map(OutDoorOrder::getId)
				.distinct().collect(Collectors.toList());
		listOfOrderId.forEach(
				order -> 
				{
					log.debug("saveOrUpdate listOfOrderId.forEach order -> ", order);
			final OutDoorOrder outDoorOrder = orderRepository.findById(order).orElseThrow();
			list.forEach(
					request -> 
					{
				final String id = StringUtils.isNotBlank(request.getId()) ? request.getId()
						: UUID.randomUUID().toString();
				log.debug("saveOrUpdate OutDoorOrderRow.forEach row -> ", request);
				try {
				repository.save(new OutDoorOrderRow(id,
						new OutDoorOrder(outDoorOrder.getId(), outDoorOrder.getComment(), outDoorOrder.getDate(),
								outDoorOrder.getDivision(), outDoorOrder.getUser(), outDoorOrder.getCustomer(),
								outDoorOrder.getSample()),
						request.getAttribute(), request.getNumber(), request.getBarcode(), 
						request.getProduct().getId(),
						request.getSize(), 
						request.getColor().getId(), 
						request.getLiner().getId(),
						request.getRant().getId(), 
						request.getShpalt().getId(), 
						request.getVstavka().getId(),
						request.getGelenok().getId(), 
						request.getGuba().getId(), 
						request.getKabluk().getId(),
						request.getMatirovka().getId(), 
						request.getPechat().getId(), 
						request.getProshiv().getId(),
						request.getPyatka().getId(), 
						request.getSled().getId(), 
						request.getSpoyler().getId(),
						request.getAshpalt().getId(), 
						request.getProdir(), 
						request.getDifersize(), 
						request.getTert(),
						request.getFrez(), 
						request.getSample(), 
						request.getPlastizol().getId(),
						request.getCreatedAt()));
				} catch (Exception e) {
					log.error("saveOrUpdate exception saving row -> ", e);
				}
			}
					);
		});
	}

	public void copyRows(String sourceId, OutDoorOrder dest) throws Exception {
		List<OutDoorOrderRow> sourceRows = repository.findByOutDoorOrderIdOrderByCreatedAtDesc(sourceId).orElseThrow();
		List<OutDoorOrderRow> destRows = new ArrayList<OutDoorOrderRow>();
		sourceRows.forEach(row -> {
			try {
				copy(row, dest);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		//saveOrUpdate(destRows);
	}

	// for sendMail only
	public List<OutDoorOrderRowReq> getRows(String id) throws Exception {
		List<OutDoorOrderRowReq> orderRep = new ArrayList<>();
		List<OutDoorOrderRow> rowList = repository.findByOutDoorOrderIdOrderByCreatedAtDesc(id).orElseThrow();
		Boolean div25 = orderRepository.findById(id).orElseThrow().getDivision().getCode()
				.equals(Constants.TEP_DIVISION);
		String tmpName;
		if (!rowList.isEmpty()) {

			for (int i = 0; i < rowList.size(); i++) {
				tmpName = rowList.get(i).getProduct().getId() != null
						? (rowList.get(i).getProduct().getId().equals("0") ? "" : rowList.get(i).getProduct().getName())
						: "";
				tmpName = tmpName.concat(rowList.get(i).getSize() != null
						? (rowList.get(i).getSize().equals("") ? "" : " р. " + rowList.get(i).getSize())
						: "");
				tmpName = tmpName.concat(
						rowList.get(i).getColor().getId() != null ? (rowList.get(i).getColor().getId().equals("0") ? ""
								: ", Цвет " + rowList.get(i).getColor().getName()) : "");
				tmpName = tmpName.concat(
						rowList.get(i).getLiner().getId() != null ? (rowList.get(i).getLiner().getId().equals("0") ? ""
								: ", Подклада " + rowList.get(i).getLiner().getName()) : "");
				tmpName = tmpName.concat(
						rowList.get(i).getRant().getId() != null ? (rowList.get(i).getRant().getId().equals("0") ? ""
								: ", Рант " + rowList.get(i).getRant().getName()) : "");
				tmpName = tmpName.concat(rowList.get(i).getShpalt().getId() != null
						? (rowList.get(i).getShpalt().getId().equals("0") ? ""
								: ", Шпальт " + rowList.get(i).getShpalt().getName())
						: "");

				tmpName = tmpName.concat(rowList.get(i).getVstavka().getId() != null
						? (rowList.get(i).getVstavka().getId().equals("0") ? ""
								: ", Вставка " + rowList.get(i).getVstavka().getName())
						: "");

				tmpName = tmpName.concat(rowList.get(i).getAshpalt().getId() != null
						? (rowList.get(i).getAshpalt().getId().equals("0") ? ""
								: ", " + (div25 ? "Шпальт" : "Прокрас") + " " + rowList.get(i).getAshpalt().getName())
						: "");

				tmpName = tmpName.concat(rowList.get(i).getSpoyler().getId() != null
						? (rowList.get(i).getSpoyler().getId().equals("0") ? ""
								: ", " + (div25 ? "Спойлер" : "М1.") + " " + rowList.get(i).getSpoyler().getName())
						: "");

				tmpName = tmpName
						.concat(rowList.get(i).getGuba().getId() != null
								? (rowList.get(i).getGuba().getId().equals("0") ? ""
										: ", " + (div25 ? "Губа" : "М2.") + " " + rowList.get(i).getGuba().getName())
								: "");

				tmpName = tmpName.concat(rowList.get(i).getKabluk().getId() != null
						? (rowList.get(i).getKabluk().getId().equals("0") ? ""
								: ", " + (div25 ? "Каблук" : "М3.") + " " + rowList.get(i).getKabluk().getName())
						: "");

				tmpName = tmpName.concat(rowList.get(i).getGelenok().getId() != null
						? (rowList.get(i).getGelenok().getId().equals("0") ? ""
								: ", Геленок " + rowList.get(i).getGelenok().getName())
						: "");

				tmpName = tmpName.concat(
						rowList.get(i).getSled().getId() != null ? (rowList.get(i).getSled().getId().equals("0") ? ""
								: ", След " + rowList.get(i).getSled().getName()) : "");

				tmpName = tmpName.concat(rowList.get(i).getPyatka().getId() != null
						? (rowList.get(i).getPyatka().getId().equals("0") ? ""
								: ", Пятка " + rowList.get(i).getPyatka().getName())
						: "");

				tmpName = tmpName
						.concat(rowList.get(i).getTert() != null ? (rowList.get(i).getTert() ? ", Терт. " : "") : "");

				tmpName = tmpName.concat(rowList.get(i).getMatirovka().getId() != null
						? (rowList.get(i).getMatirovka().getId().equals("0") ? ""
								: ", Матировка " + rowList.get(i).getMatirovka().getName())
						: "");

				tmpName = tmpName.concat(rowList.get(i).getPechat().getId() != null
						? (rowList.get(i).getPechat().getId().equals("0") ? ""
								: ", Печать " + rowList.get(i).getPechat().getName())
						: "");

				tmpName = tmpName.concat(rowList.get(i).getProshiv().getId() != null
						? (rowList.get(i).getProshiv().getId().equals("0") ? ""
								: ", Прошив " + rowList.get(i).getProshiv().getName())
						: "");

				tmpName = tmpName.concat(
						rowList.get(i).getProdir() != null ? (rowList.get(i).getProdir() ? ", Продир. " : "") : "");

				tmpName = tmpName
						.concat(rowList.get(i).getFrez() != null ? (rowList.get(i).getFrez() ? ", Фрез. " : "") : "");

				tmpName = tmpName.concat(rowList.get(i).getPlastizol().getId() != null
						? (rowList.get(i).getPlastizol().getId().equals("0") ? ""
								: ", Пл. " + rowList.get(i).getPlastizol().getName())
						: "");

				tmpName = tmpName.concat(rowList.get(i).getAttribute() != null
						? (rowList.get(i).getAttribute().equals("") ? "" : ", Доп. " + rowList.get(i).getAttribute())
						: "");

				orderRep.add(new OutDoorOrderRowReq(tmpName, rowList.get(i).getNumber()));
			}
		}

		return orderRep;
	}
}
