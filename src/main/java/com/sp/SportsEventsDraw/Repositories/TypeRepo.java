package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Type;
import org.springframework.data.repository.CrudRepository;

public interface TypeRepo extends CrudRepository<Type, Long> {
    Type findByName(String type_name);
    Type findTypeById(Long id);
}