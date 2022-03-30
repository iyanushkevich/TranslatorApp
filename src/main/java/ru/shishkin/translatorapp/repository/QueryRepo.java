package ru.shishkin.translatorapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shishkin.translatorapp.entity.QueryEntity;

@Repository
public interface QueryRepo extends CrudRepository<QueryEntity, Long> {
}
