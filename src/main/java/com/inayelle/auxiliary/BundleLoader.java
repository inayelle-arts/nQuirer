package com.inayelle.auxiliary;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public final class BundleLoader
{
	private BundleLoader() {}
	
	public static Map<String, String> load(String bundleName)
	{
		var bundle = ResourceBundle.getBundle(bundleName);
		Map<String, String> result = new HashMap<>();
		
		for (var key : bundle.keySet())
			result.put(key, bundle.getString(key));
		
		return result;
	}
}
