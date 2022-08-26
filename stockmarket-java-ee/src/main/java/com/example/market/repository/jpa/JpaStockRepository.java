package com.example.market.repository.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.market.entity.Stock;
import com.example.market.repository.StockRepository;

@Stateless
public class JpaStockRepository implements StockRepository {
    @PersistenceContext(unitName="stockmarketPU")
	private EntityManager entityManager;
	
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
	public Stock create(Stock stock) {
		entityManager.persist(stock);
		return stock;
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

}
