package com.huey.yina;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	Fragment fragmentA = new LetterFragment();
	Fragment fragmentB = new PlaneFragment();
	Fragment fragmentC = new DeveloperFragment();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar bar = getActionBar();

		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab tabA = bar.newTab().setText("To. À±ÀÌ³ª");
		ActionBar.Tab tabB = bar.newTab().setText("Plan");
		ActionBar.Tab tabC = bar.newTab().setText("Developer");
		
		tabA.setTabListener(new MyTabListener(this, fragmentA.getClass().getName()));
		tabB.setTabListener(new MyTabListener(this, fragmentB.getClass().getName()));
		tabC.setTabListener(new MyTabListener(this, fragmentC.getClass().getName()));
		
		bar.addTab(tabA);
		bar.addTab(tabB);
		bar.addTab(tabC);
		Log.d("log", "test");
	}

	protected class MyTabListener implements ActionBar.TabListener{

		private Fragment fragment;
		private Activity mActivity;
		private String fragName;
		
		public  MyTabListener(Activity activity, String _fragName){
			this.mActivity = activity;
			this.fragName = _fragName;
		}
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			fragment = Fragment.instantiate(mActivity, fragName);
			ft.add(android.R.id.content, fragment); 
		}
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			ft.remove(fragment);
			fragment = null;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
