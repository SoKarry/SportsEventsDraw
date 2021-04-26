package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Event;
import com.sp.SportsEventsDraw.domain.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface EventRepo extends CrudRepository<Event, Long> {
    Set<Event> findEventsByOwnerId(Long user_id);
}
