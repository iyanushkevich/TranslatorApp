package ru.shishkin.translatorapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shishkin.translatorapp.entity.SettingQueryEntity;

@Repository
public interface SettingQueryRepo extends CrudRepository<SettingQueryEntity, Long> {
}
