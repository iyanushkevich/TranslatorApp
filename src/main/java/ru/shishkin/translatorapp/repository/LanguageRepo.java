package ru.shishkin.translatorapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shishkin.translatorapp.entity.LanguageEntity;

@Repository
public interface LanguageRepo extends CrudRepository<LanguageEntity, String> {
}
