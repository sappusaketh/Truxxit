package com.database.querybuilders.servlet.listeners;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.database.querybuilders.constants.DatabaseConstants;
import com.datbase.querybuillders.util.DatabaseConnectivityManager;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();

		// initialize DB Connection
		String databaseUrl = servletContext.getInitParameter(DatabaseConstants.DATABASE_URL);
		String databaseUsername = servletContext.getInitParameter(DatabaseConstants.DATABASE_USER_NAME);
		String databasePassword = servletContext.getInitParameter(DatabaseConstants.DATABASE_PASSWORD);

		try {
			DatabaseConnectivityManager connectionManager = new DatabaseConnectivityManager(databaseUrl,
					databaseUsername, databasePassword);
			servletContext.setAttribute(DatabaseConstants.DATABASECONNECTION, connectionManager.getConnection());
			System.out.println("DB Connection initialized successfully.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// initialize log4j
		String log4jConfig = servletContext.getInitParameter(DatabaseConstants.LOG4J_CONSTANTS);
		if (log4jConfig == null) {
			System.err.println("No log4j-config init param, initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
		} else {
			String webAppPath = servletContext.getRealPath("/");
			String log4jProp = webAppPath + log4jConfig;
			File log4jConfigFile = new File(log4jProp);
			if (log4jConfigFile.exists()) {
				System.out.println("Initializing log4j with: " + log4jProp);
				DOMConfigurator.configure(log4jProp);
			} else {
				System.err.println(log4jProp + " file not found, initializing log4j with BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
		System.out.println("log4j configured properly");
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		Connection con = (Connection) servletContextEvent.getServletContext()
				.getAttribute(DatabaseConstants.DATABASECONNECTION);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
