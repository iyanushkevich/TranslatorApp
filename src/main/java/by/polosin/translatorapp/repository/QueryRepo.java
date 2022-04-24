package by.polosin.translatorapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import by.polosin.translatorapp.entity.QueryEntity;

@Repository
public interface QueryRepo extends CrudRepository<QueryEntity, Long> {
}
