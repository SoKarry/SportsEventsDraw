package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Event;
import com.sp.SportsEventsDraw.domain.Game;
import com.sp.SportsEventsDraw.domain.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Set;
//Интерфейс для события
public interface EventRepo extends CrudRepository<Event, Long> {
    //Поиск событий по id владельца (пользователя)
    Set<Event> findEventsByOwnerId(Long user_id);
    //Поиск событий по виду спорта
    Set<Event> findEventsByType(Type type);
    //Поиск событий по типу и части названия (используется для фильтрации данных)
    Set<Event> findEventsByTypeAndNameContaining(Type type, String name);
    //Поиск событий по части названия (используется для фильтрации данных)
    Set<Event> findEventsByNameContaining(String name);
    //Удаление события
    @Override
    void deleteById(@NonNull Long id);
}
