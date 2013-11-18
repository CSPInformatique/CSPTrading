package com.cspinformatique.csptrading.listener;

import javax.annotation.PostConstruct;

import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import com.cspinformatique.csptrading.entity.Position;
import com.cspinformatique.csptrading.entity.Stock;
import com.cspinformatique.csptrading.entity.Wallet;

@Component
public class HibernateEventWiring implements PostLoadEventListener {
	private static final long serialVersionUID = -5791133709243889892L;

	@Autowired private LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	@Autowired private PositionListener positionListener;
	@Autowired private StockListener stockListener;
	@Autowired private WalletListener walletListener;
	
	@PostConstruct
	public void registerListeners() {
        EventListenerRegistry registry =	((SessionFactoryImpl)
        										((HibernateEntityManagerFactory)entityManagerFactory.getObject())
        											.getSessionFactory()).getServiceRegistry().getService(
        												EventListenerRegistry.class
        											);
        
        registry.getEventListenerGroup(EventType.POST_LOAD).appendListener(this);
    }

	@Override
	public void onPostLoad(PostLoadEvent event) {
		Object entity = event.getEntity();

		if(entity instanceof Position){
			positionListener.handlePostLoad((Position)entity);
		}else if(entity instanceof Stock){
			stockListener.handlePostLoad((Stock)entity);
		}else if(entity instanceof Wallet){
			walletListener.handlePostLoad((Wallet)entity);
		}
	}
}
