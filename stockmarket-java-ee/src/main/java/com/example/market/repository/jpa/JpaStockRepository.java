package com.example.market.repository.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.market.application.event.StockPriceChangedEvent;
import com.example.market.entity.Stock;
import com.example.market.repository.StockRepository;

@Stateless
public class JpaStockRepository implements StockRepository {
    @PersistenceContext(unitName="stockmarketPU")
	private EntityManager entityManager;
	@Inject
	private Event<StockPriceChangedEvent> eventPublisher;
	
	@Override
	public Optional<Stock> findOne(String symbol) {
		return Optional.ofNullable(entityManager.find(Stock.class, symbol));
	}

	@Override
	public List<Stock> findAll(int pageNo, int pageSize) {
		return entityManager.createNamedQuery("Stock.findAll", Stock.class)
				            .setFirstResult(pageNo*pageSize)
				            .setMaxResults(pageSize)
				            .getResultList();
	}

	@Override
	@Transactional
	@Asynchronous
	public Future<Stock> create(Stock stock) {
		var symbol = stock.getSymbol();
		var managedStock = entityManager.find(Stock.class, symbol );
		if (Objects.nonNull(managedStock))
			throw new IllegalArgumentException("Stock already exists.");
		entityManager.persist(stock);
		return new AsyncResult<Stock>(stock);
	}

	@Override
	@Transactional
	public Optional<Stock> removeById(String symbol) {
		var managedStock = entityManager.find(Stock.class, symbol);
		if (Objects.isNull(managedStock))
		    return Optional.empty();
		entityManager.remove(managedStock);
		return Optional.of(managedStock);
	}

	@Override
	@Transactional
	public Optional<Stock> remove(Stock e) {
		return removeById(e.getSymbol());
	}

	@Override
	public List<Stock> findAllByCompany(String company) {
		return entityManager.createNamedQuery("Stock.findAllByCompany", Stock.class)
						    .setParameter("company", company)
	                        .getResultList();
	}

	@Override
	@Transactional
	public Stock update(Stock stock) {
		var symbol = stock.getSymbol();
		var managedStock = entityManager.find(Stock.class, symbol );
		if (Objects.isNull(managedStock))
			throw new IllegalArgumentException("Cannot find stock to update");
		var event = new StockPriceChangedEvent(symbol, managedStock.getPrice(), stock.getPrice());
		eventPublisher.fire(event);
		managedStock.setPrice(stock.getPrice());
		managedStock.setDescription(stock.getDescription());
		managedStock.setCompany(stock.getCompany());
		return managedStock;
	}

}
