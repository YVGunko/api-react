package com.yg.apireact.model.outDoorOrder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.apireact.EmailSenderService;
import com.yg.apireact.model.customer.Customer;
import com.yg.apireact.model.customer.CustomerRepository;
import com.yg.apireact.model.defaults.DefaultsRepository;
import com.yg.apireact.model.division.Division;
import com.yg.apireact.model.division.DivisionRepository;
import com.yg.apireact.model.outDoorOrderRow.OutDoorOrderRowService;
import com.yg.apireact.model.user.User;
import com.yg.apireact.model.user.UserRepository;
import com.yg.apireact.utils.Utils;

@Service
public class OutDoorOrderService {
	@Autowired
	OutDoorOrderRepository repo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	DivisionRepository divisionRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	DefaultsRepository defaultsRepository;
	@Autowired
	private EmailSenderService emailService;
	@Autowired
	private OutDoorOrderRowService rowService;
	
	public String getNextOrderNumber(String client_id) throws Exception {
		String responce = null;
		if (client_id != null) {
			try {
				Long l = repo.getNextOrderNumber(client_id) + 1L;
				responce = client_id + "-" + l;
			} catch (Exception e) {
				responce = client_id + "-" + 1;
			}
		}
		return responce;
	}

	public OutDoorOrderReq saveOrUpdate(OutDoorOrderReq request) throws Exception {

		Customer customer = customerRepo.findById(request.customer_id).orElseThrow();
		User user = userRepo.findById(Long.valueOf(request.user_id)).orElseThrow();
		Division division = divisionRepo.findById(request.division_code).orElseThrow();

		String orderId = StringUtils.isBlank(request.id) ? getNextOrderNumber(request.customer_id) : request.id;

		OutDoorOrder tmp = repo.save(new OutDoorOrder(orderId, request.comment,
				(request.date != null ? request.date : new Date()), division, user, customer, request.sample));
		OutDoorOrderReq responce = OutDoorOrderReq.orderToOrderReq(tmp);

		return responce;
	}

	public boolean sendMail(String id) throws Exception {

		final Customer customer = customerRepo.findById(repo.findById(id).orElseThrow().getCustomer().getId())
				.orElseThrow();
		final OutDoorOrder ord = repo.findById(id).orElseThrow();
		final String bccMail = ord.getSample()
				? defaultsRepository.findById("samplesMail").get().name
				: defaultsRepository.findById("ordersMail").get().name;
		if (customer.getEmail() == null)
			throw new Exception("Не указан eMail.");
		if (customer.getName() == null)
			throw new Exception("Не указан eMail.");


		Mail mail = new Mail();
		mail.setFrom("stilplastservicemail@gmail.com");// replace with your desired email
		mail.setMailTo(customer.getEmail());// replace with your desired email
		mail.setBcc(bccMail);
		mail.setSubject(customer.getName().concat(" №").concat(id)
				.concat(" от " + Utils.toStringOnlyDate(Utils.toLocalDate(ord.getDate()))));
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("rows", rowService.getRows(id));
		model.put("orderNumber", id);
		model.put("orderDate", Utils.toStringOnlyDate(Utils.toLocalDate(ord.getDate())));
		model.put("orderSample", ord.getSample() ? "Образцы." : "Серия." );

		mail.setProps(model);
		emailService.sendEmail(mail, "mailOrder");		

		return (true);
	}
	
}
