package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Event;
import com.sp.SportsEventsDraw.domain.Game;
import com.sp.SportsEventsDraw.domain.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface EventRepo extends CrudRepository<Event, Long> {
    Set<Event> findEventsByOwnerId(Long user_id);
    Set<Event> findEventsByType(Type type);
    Set<Event> findEventsByTypeAndNameContaining(Type type, String name);
    Set<Event> findEventsByNameContaining(String name);

    @Override
    void deleteById(@NonNull Long id);
}
