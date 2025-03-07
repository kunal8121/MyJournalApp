package com.KK.MyJournalApp.cache;

import com.KK.MyJournalApp.Entity.ConfigJournalAppEntity;
import com.KK.MyJournalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppChace {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String , String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init()
    {
        List<ConfigJournalAppEntity>  all=configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity:all)
        {
            APP_CACHE.put(configJournalAppEntity.getKey() ,  configJournalAppEntity.getValue());
        }
    }
}
