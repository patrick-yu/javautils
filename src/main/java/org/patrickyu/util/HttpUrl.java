package org.patrickyu.util;

import java.net.MalformedURLException;
import java.net.URL;


public class HttpUrl {
	private boolean right = false;
	private boolean ssl = false;
	private String host;
	private String fullPath;
	private String path;
	private String query;
	private int port = 80;

	public HttpUrl(String url) {
		try {
			URL aURL = new URL(url);
			right = true;
			if (StringUtils.equalsIgnoreCase("https", aURL.getProtocol()))
				ssl = true;
			else
				ssl = false;
			setHost(aURL.getHost());
			fullPath = aURL.getFile();
			if (StringUtils.isEmpty(fullPath))
				fullPath = "/";
			else if (!fullPath.startsWith("/"))
				fullPath = "/" + fullPath;
			path = aURL.getPath();
			if (StringUtils.isEmpty(path))
				path = "/";
			else if (!path.startsWith("/"))
				path = "/" + path;
			query = aURL.getQuery();
			port = aURL.getPort();
			if (port < 0) {
				if (ssl)
					port = 443;
				else
					port = 80;
			}
		} catch (MalformedURLException e) {
			right = false;
		}
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
