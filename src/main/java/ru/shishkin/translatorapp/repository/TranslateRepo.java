package ru.shishkin.translatorapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shishkin.translatorapp.entity.TranslateEntity;

@Repository
public interface TranslateRepo extends CrudRepository<TranslateEntity, Long> {
}
