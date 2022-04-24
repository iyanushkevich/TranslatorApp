package by.polosin.translatorapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import by.polosin.translatorapp.entity.QueryEntity;
import by.polosin.translatorapp.repository.QueryRepo;

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
