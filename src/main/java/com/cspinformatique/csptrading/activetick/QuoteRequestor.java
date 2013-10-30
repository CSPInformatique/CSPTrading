package com.cspinformatique.csptrading.activetick;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cspinformatique.csptrading.entity.Stock;

import at.feedapi.ActiveTickServerAPI;
import at.feedapi.ActiveTickServerRequester;
import at.feedapi.ActiveTickStreamListener;
import at.feedapi.FeedParser;
import at.feedapi.Helpers;
import at.feedapi.Session;
import at.shared.ATServerAPIDefines;
import at.shared.ATServerAPIDefines.ATDataType;
import at.shared.ATServerAPIDefines.ATQuoteDbResponseType;
import at.shared.ATServerAPIDefines.ATQuoteFieldType;
import at.shared.ATServerAPIDefines.ATSYMBOL;
import at.shared.ATServerAPIDefines.ATSymbolStatus;
import at.shared.ATServerAPIDefines.QuoteDbDataItem;
import at.shared.ATServerAPIDefines.QuoteDbResponseItem;
import at.shared.ActiveTick.Price;

public class QuoteRequestor extends ActiveTickServerRequester {
	private static final Logger logger = LoggerFactory.getLogger(QuoteRequestor.class);
	
	private double price;
	private Stock stock;
	
	private boolean completed;
	
	public QuoteRequestor(
		Stock stock, 
		Session session
	) {
		super(session, new ActiveTickStreamListener(session, false));
		
		this.stock = stock;
	}

	protected void OnQuoteDbResponse(
		long origRequest, 
		ATServerAPIDefines.ATQuoteDbResponseType responseType, 
		Vector<ATServerAPIDefines.QuoteDbResponseItem> vecData
	){
		try{
			if(responseType.m_atQuoteDbResponseType == ATQuoteDbResponseType.QuoteDbResponseSuccess){
				for(QuoteDbResponseItem responseItem : vecData){
					if(responseItem.m_atResponse.status.m_atSymbolStatus == ATSymbolStatus.SymbolStatusSuccess){
						for(QuoteDbDataItem quoteItem : responseItem.m_vecDataItems){
							if(quoteItem.m_dataItem.dataType.m_atDataType == ATDataType.Price){
								byte[] priceBytes = new byte[5];
								System.arraycopy(quoteItem.GetItemData(), 0, priceBytes, 0, priceBytes.length);
								
								this.price = Price.ToDouble(FeedParser.ParsePrice(priceBytes, 0));
								
								logger.info("Retreive last price " + price + " for symbol" + stock.getSymbol());
							}
						}
					}else{
						throw new RuntimeException(
							"Invalid stock quote resquest : " + 
								responseItem.m_atResponse.status.m_atSymbolStatus
						);
					}
				}
			}else{
				throw new RuntimeException("Invalid resquest : " + responseType.m_atQuoteDbResponseType);
			}
		}finally{
			completed = true;
		}
	}
	
	public double requestQuote(){
		this.completed = false;
		
		List<ATSYMBOL> symbols = Arrays.asList(new ATSYMBOL[]{Helpers.StringToSymbol(this.stock.getSymbol())});
		List<ATQuoteFieldType> fields =	Arrays.asList(
											new ATQuoteFieldType[]{
												new ATServerAPIDefines().new ATQuoteFieldType(ATQuoteFieldType.LastPrice)
											}
										);
		
		this.SendATQuoteDbRequest(
			symbols, 
			symbols.size(), 
			fields, 
			fields.size(), 
			ActiveTickServerAPI.DEFAULT_REQUEST_TIMEOUT
		);
		
		do{
			try{
				Thread.sleep(200);
			}catch(InterruptedException interruptedEx){
				throw new RuntimeException(interruptedEx);
			}
		}while(!completed);
		
		return price;
	}
}
