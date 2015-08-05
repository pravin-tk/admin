package org.school.admin.data;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class UriData {
	private String uri;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	@Override
	public String toString() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Uri", uri);
		Gson gson = new Gson();
		return gson.toJson(map);
	}
	
}