package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

//Интерфейс для видов спорта
public interface TypeRepo extends CrudRepository<Type, Long> {
    //Поиск вида по имени
    Type findByName(String type_name);
    //Поиск вида по id
    Type findTypeById(Long id);
    //Удаление вида
    @Override
    void deleteById(@NonNull Long id);
}
