package com.techwl.stn.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techwl.stn.dao.ContributionDetails;
import com.techwl.stn.repositories.ContributionsRepository;
import com.techwl.stn.repositories.UserRepository;
import com.techwl.stn.views.beans.Contributors;

@RestController
public class ContributorsController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ContributionsRepository contributionsRepository;

	@GetMapping(value = "/loadContributors")
	public List<Contributors> getContributors(@RequestParam("startDate") String date) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<ContributionDetails> allDetails  = null;
		try {
			Date parsedDate = format.parse(date);
			allDetails = contributionsRepository.findByDate(parsedDate);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
//		List<ContributionDetails> allDetails = contributionsRepository.findAll();		
		List<Contributors> list = new ArrayList<>();
		allDetails.stream().forEach(contribution -> {
			Contributors contributors = new Contributors();
			contributors.setName(contribution.getAddedBy());
			contributors.setAmount(contribution.getContribution());
			contributors.setId(contribution.getId());			
			list.add(contributors);
		});

		//Get totals
		Double contributionTotals = Double.valueOf(getContributionTotals("ALL"));
		Contributors totals = new Contributors();
		totals.setAmount(contributionTotals);
		totals.setName("Totals");
		list.add(totals);
		
		return list;
	}

	@PostMapping(value = "/saveContributions")
	public String saveContributions(@RequestBody List<Contributors> contributors) {
		// save contributions into DB.
		List<ContributionDetails> contributionList = new ArrayList<>();
		contributors.forEach(contributor -> {
			ContributionDetails contriDetails = new ContributionDetails();
			contriDetails.setId(contributionsRepository.count() + 1);
			contriDetails.setContributionDate(new Date());
			contriDetails.setAddedBy(contributor.getName());
			contriDetails.setContribution(contributor.getAmount());
			contriDetails.setContributionId((long) contributor.getId());
			contributionList.add(contriDetails);
		});

		contributionsRepository.saveAll(contributionList);
		return "success";
	}


	@GetMapping(value = "/getTotals")
	public String getContributionTotals(@RequestParam("filter") String filterName ) {		
		return contributionsRepository.getTotals();		
	}
}
