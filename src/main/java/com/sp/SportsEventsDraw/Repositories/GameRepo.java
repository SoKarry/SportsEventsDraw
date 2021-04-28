package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Event;
import com.sp.SportsEventsDraw.domain.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface GameRepo extends CrudRepository<Game, Long> {
    Set<Game> findByEvownerOrderById(Event event);
    Set<Game> findByPlayer1IdOrderById(Long id);
}
