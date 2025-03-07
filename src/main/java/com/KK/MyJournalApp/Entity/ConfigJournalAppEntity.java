package com.KK.MyJournalApp.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Data
@Document(collection="config_journal_app")
@NoArgsConstructor
public class ConfigJournalAppEntity {

    private String key;
    private String value;
}
