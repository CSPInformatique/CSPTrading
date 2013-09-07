package com.cspinformatique.csptrading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.service.PositionService;

@Controller
@RequestMapping("/position")
public class PositionController extends CspTradingController{
	@Autowired private PositionService positionService;
	
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
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method=RequestMethod.POST)
	public void savePosition(@RequestBody Position position){
		this.positionService.savePosition(position);
	}
}
