package com.huey.yina;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DeveloperFragment extends Fragment{
	private TextView tv_develop;
	private ImageView iv_develop;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_developer, container, false);
		initWidget(view);
		tv_develop.setText("키 : 180cm\n몸무게 : ??\n얼굴 : 매우 잘생김\n여자친구 : 임자 있음");
		
		return view;
	}
	private void initWidget(View view){
		tv_develop = (TextView)view.findViewById(R.id.tv_develop);
		iv_develop = (ImageView)view.findViewById(R.id.iv_develop);
	}
}
