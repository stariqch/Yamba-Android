/**
 * PostToTwitter.java
 * Yamba
 *
 * Created by Linkou Bian on Sep 6, 2012
 * Copyright (c) 2012 Balloonsys Inc. All rights reserved.
 */
package com.balloonsys.yamba.util;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author Linkou Bian
 *
 */
public class PostToTwitter extends AsyncTask<String, Integer, String> {
	
	private static final String TAG = PostToTwitter.class.getSimpleName();
	
	private Twitter twitter;

	/**
	 * Use this.getTwitter instead of twitter directly.
	 * !!!This needs to be updated for a better design!!!
	 * 
	 * @return initialized twitter object
	 */
	public Twitter getTwitter() {
		if (twitter == null) {
	        ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey("f4XW8CnYAiLM4kacJUCERQ")
			  .setOAuthConsumerSecret("6Ttjl9RYcWbnV8cN88Q9GBasMppu5HNSQ01FO3XgiZQ")
			  .setOAuthAccessToken("77163309-Sns5FjjBRKJVE0aDA9IhayBBVqrl0635S3fCtiBSw")
			  .setOAuthAccessTokenSecret("sMwgbopHJJYp1SN5KqKfb673nniPwSv6yCu7g7pFc");
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter = tf.getInstance();
		}
		
		return twitter;
	}

	@Override
	protected String doInBackground(String... params) {
		String status = "SUCCESS";
		
		try {
			this.getTwitter().updateStatus(params[0]);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			status = "FAILED";
		}
		
		return status;
	}

}
