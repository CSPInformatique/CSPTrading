package com.cspinformatique.csptrading.activetick;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.Stock;

import at.feedapi.ActiveTickServerAPI;
import at.feedapi.ActiveTickStreamListener;
import at.feedapi.Helpers;
import at.feedapi.Session;
import at.shared.ATServerAPIDefines;
import at.shared.ATServerAPIDefines.ATBarHistoryResponseType;
import at.shared.ATServerAPIDefines.ATBarHistoryType;
import at.shared.ATServerAPIDefines.ATSYMBOL;
import at.shared.ATServerAPIDefines.SYSTEMTIME;

public class QuoteHistoryRequestor extends at.feedapi.ActiveTickServerRequester{
	private static final Logger logger = LoggerFactory.getLogger(QuoteHistoryRequestor.class);
	
	private Stock stock;
	private Date startDate;
	private Date endDate;
	private DateFormat dateFormat;
	
	private boolean completed;
	
	private List<Quote> quotes;
	
	public QuoteHistoryRequestor(
		Stock stock, 
		Date startDate,
		Date endDate,
		Session session
	) {
		super(session, new ActiveTickStreamListener(session, false));
		
		this.stock = stock;
		this.startDate = startDate;
		this.endDate = endDate;
		
		
		// 2011 11 03 13 00 00
		this.dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	}

	protected void OnBarHistoryDbResponse(
		long origRequest, 
		ATServerAPIDefines.ATBarHistoryResponseType responseType, 
		Vector<ATServerAPIDefines.ATBARHISTORY_RECORD> vecData
	){
		try{
			if(responseType.m_responseType == ATBarHistoryResponseType.BarHistoryResponseSuccess){
				Date lastQuoteTimestamp = null;
				quotes = new ArrayList<Quote>();
				
				for(ATServerAPIDefines.ATBARHISTORY_RECORD record : vecData){
					
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, record.barTime.year);
					calendar.set(Calendar.MONTH, record.barTime.month - 1);
					calendar.set(Calendar.DAY_OF_MONTH, record.barTime.day);
					calendar.set(Calendar.HOUR_OF_DAY, record.barTime.hour);
					calendar.set(Calendar.MINUTE, record.barTime.minute);
					calendar.set(Calendar.SECOND, record.barTime.second);
					
					Date timestamp = calendar.getTime();
					
					if(lastQuoteTimestamp == null || lastQuoteTimestamp.getTime() < timestamp.getTime()){
						lastQuoteTimestamp = timestamp;
					}
					
					quotes.add(
						new Quote(
							stock.getId(), 
							timestamp, 
							stock.getSymbol(),
							record.open.price,
							record.close.price,
							record.volume, 
							record.high.price, 
							record.low.price
						)
					);
				}
				
				logger.info(vecData.size() + " quotes proccessed from Active Tick for " + this.stock.getSymbol() + ".");
			}else{
				throw new RuntimeException("Invalid resquest : " + responseType.m_responseType);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			completed = true;
		}
	}
	
	public List<Quote> requestQuotes(){
		this.completed = false;
		ATSYMBOL atSymbol = Helpers.StringToSymbol(this.stock.getSymbol());
		
		SYSTEMTIME beginDateTime = Helpers.StringToATTime(this.dateFormat.format(startDate));
		SYSTEMTIME endDateTime = Helpers.StringToATTime(this.dateFormat.format(endDate));
		
		logger.info("Requesting Active Tick for " + this.stock.getSymbol() + " - " + startDate + " - " + endDate);
		
		this.SendATBarHistoryDbRequest(
			atSymbol, 
			(new ATServerAPIDefines()).new ATBarHistoryType(ATBarHistoryType.BarHistoryIntraday),
			(short)1, 
			beginDateTime, 
			endDateTime, 
			ActiveTickServerAPI.DEFAULT_REQUEST_TIMEOUT
		);
		
		do{
			try{
				Thread.sleep(200);
			}catch(InterruptedException interruptedEx){
				throw new RuntimeException(interruptedEx);
			}
		}while(!completed);
		
		return this.quotes;
	}
}