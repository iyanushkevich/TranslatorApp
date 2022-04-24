package by.polosin.translatorapp.repository;

import by.polosin.translatorapp.entity.SettingQueryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingQueryRepo extends CrudRepository<SettingQueryEntity, Long> {
}
