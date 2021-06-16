package vsfam.ss.invMan.manager.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tab_auditing")
public class Auditing {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auditing_generator")
	@SequenceGenerator(name = "auditing_generator", sequenceName = "auditing_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_principal", nullable = false, length = 20)
	private String principal;
	
	@Column(name = "f_date_of_transaction", nullable = false)
	private Calendar dateOfTransaction;
	
	@Column(name = "f_type_of_transaction", nullable = false, length = 20)
	private String typeOfTransaction;
	
	@Column(name = "f_value", nullable = false, columnDefinition = "TEXT")
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Calendar getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Calendar dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}

	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Auditing(String principal, Calendar dateOfTransaction, String typeOfTransaction, String value) {
		super();
		this.principal = principal;
		this.dateOfTransaction = dateOfTransaction;
		this.typeOfTransaction = typeOfTransaction;
		this.value = value;
	}
}