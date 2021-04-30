package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Draw;
import org.springframework.data.repository.CrudRepository;

//Интерфейс для жеребьёвки
public interface DrawRepo extends CrudRepository<Draw, Long> {
    //Поиск жеребьёвки по имени
    Draw findByName(String name);
}
