package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Player;
import org.springframework.data.repository.CrudRepository;

//Интерфейс для игроков
public interface PlayerRepo extends CrudRepository<Player, Long> {
    //Поиск игрока по имени
    Player findByPlname(String plname);
}
