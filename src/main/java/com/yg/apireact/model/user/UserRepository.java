package com.yg.apireact.model.user;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long>{
	public Optional<User> findOneByName(String name);
	User findOneById(@Param("id") Long id);
}
