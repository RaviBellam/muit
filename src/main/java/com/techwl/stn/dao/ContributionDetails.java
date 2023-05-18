package com.techwl.stn.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "contribution_details")
public class ContributionDetails {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "contributed_date")
	@Temporal(TemporalType.DATE)
	private Date contributionDate;

	@Column(name = "contribution")
	@NotNull(message = "Contribution should not be empty")
	private Double contribution;

	@Column(name = "details")
	@Nullable
	private String details;

	@Column(name = "addedBy")
	@Nullable
	private String addedBy;

	@Column(name = "modified_date")
	@Nullable
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;

//	@Cascade(CascadeType.SAVE_UPDATE)
//	@ManyToOne
//	@JoinColumn(name = "contributor_id")
//	private User user;
	
	@Column(name = "contributor_id")
	@NotNull(message = "contributorId should not be empty")
	private Long contributionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getContributionDate() {
		return contributionDate;
	}

	public void setContributionDate(Date contributionDate) {
		this.contributionDate = contributionDate;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getContribution() {
		return contribution;
	}

	public void setContribution(Double contribution) {
		this.contribution = contribution;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Long getContributionId() {
		return contributionId;
	}

	public void setContributionId(Long contributionId) {
		this.contributionId = contributionId;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
}
