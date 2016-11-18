package br.edu.insper.recfacial.servlets;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebCam implements ServletContextListener {

	private ExecutorService executor;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		executor.shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		executor = Executors.newSingleThreadExecutor();
		executor.submit(new Task());
	}
	
	
}
