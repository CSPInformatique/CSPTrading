package com.cspinformatique.csptrading.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cspinformatique.csptrading.util.MarketUtil;

@Controller
@RequestMapping("/quote")
public class QuoteController {
	@Autowired private QuoteGapController quoteGapController;
	
	private DateFormat dateFormat;
	
	public QuoteController(){
		this.dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	}
	
	@RequestMapping
	public String getQuoteGap(Model model){		
		return "quote";
	}
	
	@RequestMapping(params="dates")
	public @ResponseBody List<String> getAnalysisDates(){		
		List<String> formatedDates = new ArrayList<String>();
		for(Date date : MarketUtil.getOpenedDatesSinceDays(10)){
			formatedDates.add(dateFormat.format(date));
		}
		
		return formatedDates;
	}
}
