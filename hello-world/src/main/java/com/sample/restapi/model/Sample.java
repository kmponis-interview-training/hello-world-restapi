package com.sample.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@SuppressWarnings("deprecation")
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "sample", uniqueConstraints = @UniqueConstraint(columnNames = { "user" }))
public class Sample {

	public Sample() {
		// Fixed error: No default constructor for entity when findAll()
	}

	@Id
	@SequenceGenerator(name = "mysql_database_sequence", sequenceName = "dbsequence", initialValue = 10000, allocationSize = 1)
	@GeneratedValue(generator = "mysql_database_sequence")
	private long id;

	@Index(name = "index_user")
	@Column(name = "user", nullable = false)
	private String user;

	@Column(name = "count_visit")
	private int countVisit;

}
