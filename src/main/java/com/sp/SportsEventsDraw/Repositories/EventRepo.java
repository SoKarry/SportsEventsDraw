package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepo extends CrudRepository<Event, Long> {
}
