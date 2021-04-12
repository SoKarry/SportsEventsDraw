package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepo extends CrudRepository<Player, Long> {
    Player findByPlname(String plname);
}
