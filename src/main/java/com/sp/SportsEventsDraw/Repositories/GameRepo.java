package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepo extends CrudRepository<Game, Long> {
}
