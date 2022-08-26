package com.example.market.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, PK> {
	Optional<E> findOne(PK key);

	List<E> findAll(int pageNo, int pageSize);

	E create(E e);

	Optional<E> removeById(PK key);

	Optional<E> remove(E e);

}
