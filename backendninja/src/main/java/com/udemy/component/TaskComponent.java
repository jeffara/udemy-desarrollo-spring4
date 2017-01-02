package com.udemy.component;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskComponent")
public class TaskComponent {
	
	private static final Log LOGGER = LogFactory.getLog(TaskComponent.class);
	
	//Tarefa sera executada em 5 segundos, o valor recebido em fixedDelay eh em milisegundos
	@Scheduled(fixedDelay=5000)
	public void doTask() {
		LOGGER.info("TIME IS: " + new Date());
	}
}
