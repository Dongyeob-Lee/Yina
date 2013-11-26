package com.huey.yina;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlanListAdapater extends ArrayAdapter<Plan> {
	private LayoutInflater mInflater;

	public PlanListAdapater(Context context, ArrayList<Plan> objects) {
		super(context, 0, objects);

		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View view = null;

		// 현재 리스트의 하나의 항목에 보일 컨트롤 얻기

		if (v == null) {
			// XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
			view = mInflater.inflate(R.layout.plan_list_row, null);
		} else {

			view = v;
		}

		// 자료를 받는다.
		final Plan plan = this.getItem(position);

		if (plan != null) {
			// textview setting
			TextView tv_plan_list_row = (TextView)view.findViewById(R.id.tv_plan_list_row);
			tv_plan_list_row.setText(plan.getContents());
		}
		return view;

	}
}
