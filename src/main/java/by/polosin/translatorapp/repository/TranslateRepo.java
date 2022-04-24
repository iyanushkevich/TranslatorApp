package by.polosin.translatorapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import by.polosin.translatorapp.entity.TranslateEntity;

@Repository
public interface TranslateRepo extends CrudRepository<TranslateEntity, Long> {
}
