package com.app.feed.core.rss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * Class For Hit URL RSS Feeds
 *
 * @author taufuk.muliahadi (&copy;Jul 8, 2019)
 */
@Component
public class GetFeedRss {

	private static final Logger logger = LogManager.getLogger(GetFeedRss.class);

	public String getReponseFromURL(String domain, HttpMethod httpMethod, Map<String, String> header) {
		URL url = null;
		StringBuffer response = new StringBuffer("");
		
		try {
			url = new URL(domain);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod(httpMethod.name());

			// set Header
			if (header != null) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					con.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}

			con.setDoOutput(true);

			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine);
				}
			}
		} catch (IOException e) {
			logger.error("Get Url Error", e);
		}
		return response.toString().trim();
	}
}
