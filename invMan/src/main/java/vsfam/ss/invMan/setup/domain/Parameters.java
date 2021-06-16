package vsfam.ss.invMan.setup.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tab_parameters")
public class Parameters implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8174056253542380703L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameters_generator")
	@SequenceGenerator(name = "parameters_generator", sequenceName = "parameters_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_site_url", length=255)
	private String siteUrl;
	
	@Column(name = "f_mail_enable")
	private Boolean mailEnable;
	
	@Column(name = "f_mail_host", length=255)
	private String mailHost;
	
	@Column(name = "f_mail_port")
	private Integer mailPort;
	
	@Column(name = "f_mail_username", length=255)
	private String mailUsername;
	
	@Column(name = "f_mail_password", length=255)
	private String mailPassword;
	
	@Column(name = "f_mail_transport_protocol", length=50)
	private String mailTransportProtocol;
	
	@Column(name = "f_mail_smtp_port")
	private Integer mailSmtpPort;
	
	@Column(name = "f_mail_smtp_auth")
	private Boolean mailSmtpAuth;
	
	@Column(name = "f_mail_smtp_starttls_enable")
	private Boolean mailSmtpStarttlsEnable;
	
	@Column(name = "f_mail_smtp_starttls_required")
	private Boolean mailSmtpStarttlsRequired;
	
	@Column(name = "f_from_email", length=255)
	private String fromEmail;
	
	@Column(name = "f_email_signature", length=255)
	private String emailSignature;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public Boolean getMailEnable() {
		return mailEnable;
	}

	public void setMailEnable(Boolean mailEnable) {
		this.mailEnable = mailEnable;
	}

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public Integer getMailPort() {
		return mailPort;
	}

	public void setMailPort(Integer mailPort) {
		this.mailPort = mailPort;
	}

	public String getMailUsername() {
		return mailUsername;
	}

	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getMailTransportProtocol() {
		return mailTransportProtocol;
	}

	public void setMailTransportProtocol(String mailTransportProtocol) {
		this.mailTransportProtocol = mailTransportProtocol;
	}

	public Integer getMailSmtpPort() {
		return mailSmtpPort;
	}

	public void setMailSmtpPort(Integer mailSmtpPort) {
		this.mailSmtpPort = mailSmtpPort;
	}

	public Boolean getMailSmtpAuth() {
		return mailSmtpAuth;
	}

	public void setMailSmtpAuth(Boolean mailSmtpAuth) {
		this.mailSmtpAuth = mailSmtpAuth;
	}

	public Boolean getMailSmtpStarttlsEnable() {
		return mailSmtpStarttlsEnable;
	}

	public void setMailSmtpStarttlsEnable(Boolean mailSmtpStarttlsEnable) {
		this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
	}

	public Boolean getMailSmtpStarttlsRequired() {
		return mailSmtpStarttlsRequired;
	}

	public void setMailSmtpStarttlsRequired(Boolean mailSmtpStarttlsRequired) {
		this.mailSmtpStarttlsRequired = mailSmtpStarttlsRequired;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getEmailSignature() {
		return emailSignature;
	}

	public void setEmailSignature(String emailSignature) {
		this.emailSignature = emailSignature;
	}
	
	@Override
	public String toString() {
		String str = "parameters [";		
		return str;
	}

	public String getAuditString() {
		return this.toString();
	}
}
