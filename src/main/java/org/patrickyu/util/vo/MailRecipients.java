package org.patrickyu.util.vo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class MailRecipients {
	private List<InternetAddress> recipients = new ArrayList<InternetAddress>();
	private List<InternetAddress> blindCarbonCopies = new ArrayList<InternetAddress>();
	private List<InternetAddress> carbonCopies = new ArrayList<InternetAddress>();

	public Address[] getRecipients() {
		return recipients.toArray(new Address[0]);
	}
	public Address[] getBlindCarbonCopies() {
		return blindCarbonCopies.toArray(new Address[0]);
	}
	public Address[] getCarbonCopies() {
		return carbonCopies.toArray(new Address[0]);
	}
	public MailRecipients addRecipient(String email, String name) {
		try {
			recipients.add(new InternetAddress(email, name));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	public MailRecipients clear() {
		recipients.clear();
		blindCarbonCopies.clear();
		carbonCopies.clear();
		return this;
	}
	public MailRecipients clearRecipients() {
		recipients.clear();
		return this;
	}
	public MailRecipients clearCarbonCopies() {
		carbonCopies.clear();
		return this;
	}
	public MailRecipients clearBlindCarbonCopies() {
		blindCarbonCopies.clear();
		return this;
	}
	public MailRecipients addRecipient(String email) {
		try {
			recipients.add(new InternetAddress(email));
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return this;
	}

	public MailRecipients addCarbonCopy(String email, String name) {
		try {
			carbonCopies.add(new InternetAddress(email, name));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	public MailRecipients addCarbonCopy(String email) {
		try {
			carbonCopies.add(new InternetAddress(email));
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return this;
	}

	public MailRecipients addBlindCarbonCopy(String email, String name) {
		try {
			blindCarbonCopies.add(new InternetAddress(email, name));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	public MailRecipients addBlindCarbonCopy(String email) {
		try {
			blindCarbonCopies.add(new InternetAddress(email));
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return this;
	}

}
