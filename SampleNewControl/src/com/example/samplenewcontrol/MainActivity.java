package com.example.samplenewcontrol;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private ListView lvTest;
		private LinearLayout lnMenu;
		
		boolean mIsScrollingUp = false, mIsScrollingDown = false;
		
		int mLastFirstVisibleItem;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			lvTest = (ListView)rootView.findViewById(R.id.lvTest);
			lvTest.setOverscrollFooter(null);
			lvTest.setOverscrollHeader(null);
			lvTest.setOverScrollMode(View.OVER_SCROLL_NEVER);
			lnMenu = (LinearLayout)rootView.findViewById(R.id.lnMenu);
			return rootView;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			
			String[] team = new String[]{
				"Germany", 
				"England", 
				"Italy", 
				"Netherland", 
				"Spain", 
				"Argentina",
				"Brazil", 
				"Indonesia", 
				"Japan", 
				"South Korea",
				"Belgium", 
				"France"
			};
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1,
					team);
			lvTest.setAdapter(adapter);
			
			lvTest.setOnScrollListener(new OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					// TODO Auto-generated method stub
					final ListView lw = lvTest;

				       if(scrollState == 0) {
				    	   Log.d("tes", "scrolling stopped...");
				       }
				       if (view.getId() == lw.getId()) {
				    	   final int currentFirstVisibleItem = lw.getFirstVisiblePosition();
				    	   if (currentFirstVisibleItem > mLastFirstVisibleItem) {
				    		   mIsScrollingUp = false;
				    		   slideToTop(lnMenu);
				    		   Log.d("tes", "scrolling down...");
				    		   //Toast.makeText(getActivity(), "Scrolling Down", Toast.LENGTH_LONG).show();
				    	   } else if (currentFirstVisibleItem < mLastFirstVisibleItem) {
				    		   mIsScrollingUp = true;
				    		   Log.d("tes", "scrolling up...");
				    		   slideToBottom(lnMenu);
				    		   //Toast.makeText(getActivity(), "Scrolling Up", Toast.LENGTH_LONG).show();
				    	   }

				        mLastFirstVisibleItem = currentFirstVisibleItem;
				
				       }
				}
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		// To animate view slide out from top to bottom
		public void slideToBottom(View view){
			TranslateAnimation animate = new TranslateAnimation(0,0,0,view.getHeight());
			animate.setDuration(500);
			animate.setFillAfter(true);
			view.startAnimation(animate);
			view.setVisibility(View.GONE);
		}

		// To animate view slide out from bottom to top
		public void slideToTop(View view){
			TranslateAnimation animate = new TranslateAnimation(0,0,view.getHeight(),0);
			animate.setDuration(500);
			animate.setFillAfter(true);
			view.startAnimation(animate);
			view.setVisibility(View.GONE);
		}
	}
	
	
	
}
