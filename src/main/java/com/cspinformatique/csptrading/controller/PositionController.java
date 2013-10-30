package com.cspinformatique.csptrading.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.service.PositionService;

@Controller
@RequestMapping("/position")
public class PositionController extends CspTradingController{
	@Autowired private PositionService positionService;
	
	@RequestMapping(
		value="/{position}",
		method=RequestMethod.POST, 
		params={"closeDate", "price"}
	)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody void closePosition(
		@PathVariable Position position, 
		@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date closeDate, 
		double price
	){
		this.positionService.closePosition(position, closeDate, price);
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/{position}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Position getPosition(@PathVariable Position position){
		return position;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Position> getPositions(){
		return this.positionService.getPositions();
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="text/html", params="closed")
	public String getClosedPositionsPage(){
		return "position/closed";
	}
	
	@RequestMapping(method=RequestMethod.GET, produces="text/html")
	public String getOpenPositionsPage(){
		return "position/open";
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method=RequestMethod.POST, produces="application/json")
	public @ResponseBody void savePosition(@RequestBody Position position){
		this.positionService.savePosition(position);
	}
}
