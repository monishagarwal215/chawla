package com.example.chawla;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import com.example.chawla.constants.ApiConstants;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends Activity implements ViewFactory {

	private ImageSwitcher		imgTopLeft;
	private ImageSwitcher		imgTopRight;
	private ImageSwitcher		imgBottomLeft;
	private ImageSwitcher		imgBottomRight;
	private ImageSwitcher		imgMenuLeft;
	private ImageSwitcher		imgMenuRight;
	private LinearLayout		layoutMenuLeft;
	private LinearLayout		layoutMenuRight;
	private TextView menuLeft,menuRight;
	
	private Handler				handler			= new Handler();
	private static final int[]	TOP_RIGHT		= { R.drawable.add_01,
			R.drawable.add_02					};
	private static final int[]	TOP_LEFT		= { R.drawable.add_03,
			R.drawable.add_04					};
	private static final int[]	BOTTOM_RIGHT	= { R.drawable.add_07,
			R.drawable.add_08					};
	private static final int[]	BOTTOM_LEFT		= { R.drawable.add_05,
			R.drawable.add_06					};
	private static final int[]	MENU_LEFT		= { R.drawable.menu_01_bg,
			R.drawable.menu_03_bg				};
	private static final int[]	MENU_RIGHT		= { R.drawable.menu_02_bg,
			R.drawable.menu_04_bg				};

	volatile int				a				= 0;
	volatile int				i				= 0;
	private String				menu1;
	private String				menu;
	private LayoutInflater		mInflater;
	private Animation			animationFadeOut;
	private Animation			animationFadeIn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mInflater = LayoutInflater.from(getBaseContext());
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

		imgTopLeft = (ImageSwitcher) findViewById(R.id.imgTopLeft);
		imgTopRight = (ImageSwitcher) findViewById(R.id.imgTopRight);
		imgBottomLeft = (ImageSwitcher) findViewById(R.id.imgBottomLeft);
		imgBottomRight = (ImageSwitcher) findViewById(R.id.imgBottomRight);
		imgMenuLeft = (ImageSwitcher) findViewById(R.id.imgMenuLeft);
		imgMenuRight = (ImageSwitcher) findViewById(R.id.imgMenuRight);
		layoutMenuLeft = (LinearLayout) findViewById(R.id.layoutMenuLeft);
		layoutMenuRight = (LinearLayout) findViewById(R.id.layoutMenuRight);

		imgTopLeft.setFactory(this);
		imgTopRight.setFactory(this);
		imgBottomLeft.setFactory(this);
		imgBottomRight.setFactory(this);
		imgMenuLeft.setFactory(this);
		imgMenuRight.setFactory(this);

		imgTopLeft.setInAnimation(this, R.anim.slide_in_left);
		imgTopLeft.setOutAnimation(this, R.anim.slide_out_left);

		imgBottomLeft.setInAnimation(this, R.anim.slide_in_left);
		imgBottomLeft.setOutAnimation(this, R.anim.slide_out_left);

		imgTopRight.setInAnimation(this, R.anim.slide_in_right);
		imgTopRight.setOutAnimation(this, R.anim.slide_out_right);

		imgBottomRight.setInAnimation(this, R.anim.slide_in_right);
		imgBottomRight.setOutAnimation(this, R.anim.slide_out_right);

		imgBottomRight.setInAnimation(this, R.anim.slide_in_right);
		imgBottomRight.setOutAnimation(this, R.anim.slide_out_right);

		imgMenuLeft.setInAnimation(this, R.anim.slide_up);
		imgMenuLeft.setOutAnimation(this, R.anim.slide_down);

		imgMenuRight.setInAnimation(this, R.anim.slide_up);
		imgMenuRight.setOutAnimation(this, R.anim.slide_down);
		
		menuLeft=(TextView)findViewById(R.id.menu_left);
		menuRight=(TextView)findViewById(R.id.menu_right);
		
		handler.postDelayed(timerRunnable, 2000);

		getMenu();
		getMenu1();

	}

	private void getMenu1() {
		menuRight.setText("Menu2");
		ParseQuery<ParseObject> querry = ParseQuery.getQuery(ApiConstants.ENTITY.MENU1);
		querry.whereEqualTo(ApiConstants.PARAMS.TYPE, "menu");
		querry.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> parseObject, ParseException e) {
				if (e == null) {
					addMenu(layoutMenuRight, parseObject);
				}
			}
		});
	}

	private void getMenu() {
		menuLeft.setText("Menu1");
		ParseQuery<ParseObject> querry = ParseQuery.getQuery(ApiConstants.ENTITY.MENU);
		querry.whereEqualTo(ApiConstants.PARAMS.TYPE, "menu");
		querry.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> parseObject, ParseException e) {
				if (e == null) {
					addMenu(layoutMenuLeft, parseObject);
				}
			}
		});
	}
	private void getMenu3() {
		menuLeft.setText("Menu3");
		ParseQuery<ParseObject> querry = ParseQuery.getQuery("Menu3");
		querry.whereEqualTo(ApiConstants.PARAMS.TYPE, "menu");
		querry.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> parseObject, ParseException e) {
				if (e == null) {
					addMenu(layoutMenuLeft, parseObject);
				}
			}
		});
	}
	private void getMenu2() {
		menuRight.setText("Menu4");
		ParseQuery<ParseObject> querry = ParseQuery.getQuery("Menu2");
		querry.whereEqualTo(ApiConstants.PARAMS.TYPE, "menu");
		querry.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> parseObject, ParseException e) {
				if (e == null) {
					addMenu(layoutMenuRight, parseObject);
				}
			}
		});
	}

	protected void addMenu(LinearLayout linearLayout, List<ParseObject> parseObject) {
		linearLayout.removeAllViews();
		for (ParseObject temp : parseObject) {
			View rowView = mInflater.inflate(R.layout.layout_menu, null);
			TextView txtMenuName = (TextView) rowView.findViewById(R.id.txtMenuName);
			TextView txtMenuPrice = (TextView) rowView.findViewById(R.id.txtMenuPrice);
			txtMenuName.setText(temp.getString(ApiConstants.PARAMS.NAME));
			txtMenuPrice.setText(temp.getString(ApiConstants.PARAMS.PRICE));
			linearLayout.addView(rowView);
		}
	}

	Runnable	timerRunnable	= new Runnable() {
									@Override
									public void run() {
										if (i == 0) {
											i++;
											onSwitch(imgTopLeft);
										} else if (i == 1) {
											i++;
											onSwitch(imgTopRight);
										} else if (i == 2) {
											i++;
											onSwitch(imgBottomLeft);
										} else if (i == 3) {
											i++;
											onSwitch(imgBottomRight);
										} else if (i == 4) {
											i++;
											onSwitch(imgMenuLeft);
										} else if (i == 5) {
											i++;
											i = 0;
											onSwitch(imgMenuRight);
										}
										handler.postDelayed(this, 4000);
									}
								};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public View makeView() {
		ImageView imageView = new ImageView(MainActivity.this);
		return imageView;
	}

	public void onSwitch(View view) {
		if (a == 0) {
			a++;
		} else {
			a--;
		}
		// Log.d("a : ", a + "");
		switch (view.getId()) {
		case R.id.imgBottomLeft:
			imgBottomLeft.setImageResource(BOTTOM_LEFT[a]);
			break;
		case R.id.imgBottomRight:
			imgBottomRight.setImageResource(BOTTOM_RIGHT[a]);
			break;
		case R.id.imgTopLeft:
			imgTopLeft.setImageResource(TOP_LEFT[a]);
			break;
		case R.id.imgTopRight:
			imgTopRight.setImageResource(TOP_RIGHT[a]);
			break;
		case R.id.imgMenuLeft:
			imgMenuLeft.setImageResource(MENU_LEFT[a]);
			if (layoutMenuLeft.getVisibility() == View.GONE) {
				layoutMenuLeft.setVisibility(View.VISIBLE);
			}
			layoutMenuLeft.startAnimation(animationFadeIn);
			layoutMenuLeft.startAnimation(animationFadeOut);
			if(a%2==0){
				getMenu3();
			}else{
				getMenu();
			}
			break;
		case R.id.imgMenuRight:
			imgMenuRight.setImageResource(MENU_RIGHT[a]);
			if (layoutMenuRight.getVisibility() == View.GONE) {
				layoutMenuRight.setVisibility(View.VISIBLE);
			}
			layoutMenuRight.startAnimation(animationFadeIn);
			layoutMenuRight.startAnimation(animationFadeOut);
			if(a%2==0){
				getMenu2();
			}else{
				
				getMenu1();
			}
			a++;
			break;
		default:
			break;
		}
	}
}
