package com.cspinformatique.csptrading.activetick;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.cspinformatique.csptrading.entity.Quote;
import com.cspinformatique.csptrading.entity.Stock;

import at.feedapi.ActiveTickServerAPI;
import at.feedapi.ActiveTickServerRequester;
import at.feedapi.ActiveTickStreamListener;
import at.feedapi.FeedParser;
import at.feedapi.Helpers;
import at.feedapi.Session;
import at.shared.ATServerAPIDefines;
import at.shared.ATServerAPIDefines.ATQuoteDbResponseType;
import at.shared.ATServerAPIDefines.ATQuoteFieldType;
import at.shared.ATServerAPIDefines.ATSYMBOL;
import at.shared.ATServerAPIDefines.ATSymbolStatus;
import at.shared.ATServerAPIDefines.QuoteDbDataItem;
import at.shared.ATServerAPIDefines.QuoteDbResponseItem;
import at.shared.ActiveTick.Price;

public class QuoteRequestor extends ActiveTickServerRequester {	
	private Quote quote;
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
		byte[] priceBytes = new byte[5];
		byte[] longBytes = new byte[8];
		
		try{
			if(responseType.m_atQuoteDbResponseType == ATQuoteDbResponseType.QuoteDbResponseSuccess){
				for(QuoteDbResponseItem responseItem : vecData){
					this.quote = new Quote();
					if(responseItem.m_atResponse.status.m_atSymbolStatus == ATSymbolStatus.SymbolStatusSuccess){
						
						for(QuoteDbDataItem quoteItem : responseItem.m_vecDataItems){							
							switch(quoteItem.m_dataItem.fieldType.m_atQuoteFieldType){
								case ATQuoteFieldType.LastPrice:{
									
									System.arraycopy(quoteItem.GetItemData(), 0, priceBytes, 0, priceBytes.length);
									
									this.quote.setLow(Price.ToDouble(FeedParser.ParsePrice(priceBytes, 0)));
									break;
								} case ATQuoteFieldType.Volume:{
									System.arraycopy(quoteItem.GetItemData(), 0, longBytes, 0, 8);
									this.quote.setVolume(ByteBuffer.wrap(longBytes).order(ByteOrder.LITTLE_ENDIAN).getLong());
									break;
								}
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
	
	public Quote requestQuote(){
		this.completed = false;
		
		List<ATSYMBOL> symbols = Arrays.asList(new ATSYMBOL[]{Helpers.StringToSymbol(this.stock.getSymbol())});
		ATServerAPIDefines apiDefines = new ATServerAPIDefines();
		List<ATQuoteFieldType> fields =	Arrays.asList(
											new ATQuoteFieldType[]{
													apiDefines.new ATQuoteFieldType(ATQuoteFieldType.LastPrice),
													apiDefines.new ATQuoteFieldType(ATQuoteFieldType.Volume),
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
		
		return this.quote;
	}
}
