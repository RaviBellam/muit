package com.techwl.stn.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techwl.stn.dao.ContributionDetails;

public interface ContributionsRepository extends JpaRepository<ContributionDetails, Integer>{
	
	@Query(value = "select c from ContributionDetails c where c.contributionDate = :date order by contributionDate, addedBy DESC")		
	public List<ContributionDetails> findByDate(@Param("date")Date date);

	@Query(value = "select sum(contribution) from ContributionDetails c")		
	public String getTotals();
	
	

	
	
}
