package com.cspinformatique.csptrading.listener;

public interface EntityListener<T>{
	public void handlePostLoad(T entity);
}