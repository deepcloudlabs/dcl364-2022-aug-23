package com.example.market.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

public interface CrudRepository<E, PK> {
	Optional<E> findOne(PK key);

	List<E> findAll(int pageNo, int pageSize);

	Future<E> create(E e);
	
	E update(E e);

	Optional<E> removeById(PK key);

	Optional<E> remove(E e);

}
