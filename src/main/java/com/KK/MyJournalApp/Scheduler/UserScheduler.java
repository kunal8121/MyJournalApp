package com.KK.MyJournalApp.Scheduler;

import com.KK.MyJournalApp.Entity.JournalEntry;
import com.KK.MyJournalApp.Entity.User;
import com.KK.MyJournalApp.cache.AppChace;
import com.KK.MyJournalApp.enums.Sentiment;
import com.KK.MyJournalApp.repository.UserRepositoryImpl;
import com.KK.MyJournalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;


    @Autowired
    private AppChace appChace;


    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

//    @Scheduled(cron="0 9 * * SUN")
    public void fetchUsersAndSendSaMail()
    {
        List<User> users=userRepositoryImpl.getUserForSA();

        for(User  user: users)
        {
            List<JournalEntry> journalEntries=user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x-> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment , Integer> sentimentCounts=new HashMap<>();
            for(Sentiment sentiment: sentiments)
            {
                if(sentiment!=null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment,0)+1);
            }
            Sentiment mostFrequentSentiment=null;
            int maxCount=0;
            for(Map.Entry<Sentiment,Integer> entry : sentimentCounts.entrySet())
            {
                if(entry.getValue()>maxCount)
                {
                    maxCount=entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if(mostFrequentSentiment !=null)
            {
                emailService.sendEmail(user.getEmail(), "Sentiment after 7 days", mostFrequentSentiment.toString());
            }

        }
    }
}