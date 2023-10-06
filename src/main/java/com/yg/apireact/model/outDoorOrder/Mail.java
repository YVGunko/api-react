package com.yg.apireact.model.outDoorOrder;

import java.util.List;
import java.util.Map;

import com.yg.apireact.model.outDoorOrderRow.OutDoorOrderRowReq;

public class Mail {

	private String from;
	private String mailTo;
	private String Bcc;
	private String subject;
	private String date;
	private String sign;

	private List<OutDoorOrderRowReq> rows;
	private Map<String, Object> props;

	public Mail() {
	}

	public String getBcc() {
		return Bcc;
	}

	public void setBcc(String bcc) {
		Bcc = bcc;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Map<String, Object> getProps() {
		return props;
	}

	public void setProps(Map<String, Object> props) {
		this.props = props;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<OutDoorOrderRowReq> getRows() {
		return rows;
	}

	public void setRows(List<OutDoorOrderRowReq> rows) {
		this.rows = rows;
	}

}