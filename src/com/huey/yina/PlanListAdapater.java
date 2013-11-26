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

	// �������� ��Ÿ���� �ڽ��� ���� xml�� ���̱� ���� ����
	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View view = null;

		// ���� ����Ʈ�� �ϳ��� �׸� ���� ��Ʈ�� ���

		if (v == null) {
			// XML ���̾ƿ��� ���� �о ����Ʈ�信 ����
			view = mInflater.inflate(R.layout.plan_list_row, null);
		} else {

			view = v;
		}

		// �ڷḦ �޴´�.
		final Plan plan = this.getItem(position);

		if (plan != null) {
			// textview setting
			TextView tv_plan_list_row = (TextView)view.findViewById(R.id.tv_plan_list_row);
			tv_plan_list_row.setText(plan.getContents());
		}
		return view;

	}
}
