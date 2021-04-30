package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Event;
import com.sp.SportsEventsDraw.domain.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

//Интерфейс для игр
public interface GameRepo extends CrudRepository<Game, Long> {
    //Поиск игр по id события, к которому они принадлежат
    Set<Game> findByEvownerOrderById(Event event);
    //Поиск игр по id игрока №1 с сортировкой
    Set<Game> findByPlayer1IdOrderById(Long id);
}
