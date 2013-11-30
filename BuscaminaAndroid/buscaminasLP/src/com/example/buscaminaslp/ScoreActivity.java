
package com.example.buscaminaslp;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TextView;

public class ScoreActivity extends TabActivity {

	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
		mTabHost = getTabHost();
		TabHost.TabSpec spec;
	
		spec = mTabHost.newTabSpec("facil")
				.setIndicator("Facil", null)
				.setContent(R.id.tabLayout);
		mTabHost.addTab(spec);
		
		spec = mTabHost.newTabSpec("medio")
				.setIndicator("Intermedio")
				.setContent(R.id.tabLayout);
		mTabHost.addTab(spec);
		
		spec = mTabHost.newTabSpec("dificil")
				.setIndicator("Dificil")
				.setContent(R.id.tabLayout);
		mTabHost.addTab(spec);
		
		
		/*if(mTabHost.getT){
			label.setText("FaCicL");
		}else if(mTabHost.getCurrentTabTag().equals("INTERMEDIO")){
			label.setText("InTermedio");
		}else if(mTabHost.getCurrentTabTag().equals("DIFICIL")){
			label.setText("DIfiCIL");
		}*/
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
		return true;
	}

}
