package by.polosin.translatorapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import by.polosin.translatorapp.entity.LanguageEntity;

@Repository
public interface LanguageRepo extends CrudRepository<LanguageEntity, String> {
}
