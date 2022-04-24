package ru.shishkin.translatorapp.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shishkin.translatorapp.entity.QueryEntity;
import ru.shishkin.translatorapp.repository.QueryRepo;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Service
@AllArgsConstructor
public class QueryService {
    private final QueryRepo queryRepo;
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    public QueryEntity create(HttpServletRequest request) {
        QueryEntity queryEntity = new QueryEntity();

        queryEntity.setIpAddress(request.getRemoteAddr());
        queryEntity.setTime(formatter.format(new Date(System.currentTimeMillis())));

        queryRepo.save(queryEntity);
        return queryEntity;
    }

}
