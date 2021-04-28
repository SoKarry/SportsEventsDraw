package com.sp.SportsEventsDraw.Repositories;

import com.sp.SportsEventsDraw.domain.Draw;
import org.springframework.data.repository.CrudRepository;

public interface DrawRepo extends CrudRepository<Draw, Long> {
    Draw findByName(String name);
}
