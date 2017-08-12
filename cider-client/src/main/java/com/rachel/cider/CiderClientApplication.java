package com.rachel.cider;

import com.rachel.cider.vo.TelegramMessageReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("com.rachel.cider")
public class CiderClientApplication {

	@Autowired
	TelegramMessageReceiver receiver;

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(CiderClientApplication.class, args);
	}

	@PostConstruct
	public void startTelegramBot(){
		initiateTelegramBot();
	}

	public void initiateTelegramBot (){
		TelegramBotsApi botsApi = new TelegramBotsApi();

		try {
			botsApi.registerBot(receiver);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
