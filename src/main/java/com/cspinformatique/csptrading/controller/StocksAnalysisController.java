package com.cspinformatique.csptrading.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cspinformatique.csptrading.entity.StocksAnalysis;
import com.cspinformatique.csptrading.entity.StocksAnalysisResult;
import com.cspinformatique.csptrading.service.StocksAnalysisResultService;
import com.cspinformatique.csptrading.service.StocksAnalysisService;

@Controller
@RequestMapping("/stocksAnalysis")
public class StocksAnalysisController {
	@Autowired private StocksAnalysisResultService stocksAnalysisResultService;
	@Autowired private StocksAnalysisService stocksAnalysisService;
	
	@PostConstruct
	public void init(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		this.startAnalysis(
			new StocksAnalysis(0, 1f, 0l, calendar.getTime(), new Date(), 0, 0)
		);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void startAnalysis(@RequestBody StocksAnalysis stocksAnalysis){
		this.stocksAnalysisService.startAnalysis(stocksAnalysis);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<StocksAnalysis> getStocksAnalysis(){
		return this.stocksAnalysisService.findAll();
	}
	
	@RequestMapping(value="/{stocksAnalysis}/result", method=RequestMethod.GET)
	public @ResponseBody List<StocksAnalysisResult> getStocksAnalysisResult(@PathVariable StocksAnalysis stocksAnalysis){
		return this.stocksAnalysisResultService.findByStocksAnalysis(stocksAnalysis);
	}
}
