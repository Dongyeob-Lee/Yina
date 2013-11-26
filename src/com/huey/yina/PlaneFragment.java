package com.huey.yina;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class PlaneFragment  extends Fragment{
	private EditText et_input_plan;
	private Button btn_input_plan;
	private ListView lv_plan;
	private PlanListAdapater planListAdapater;
	PlanDbAdapter planDbAdapter;
	private ArrayList<Plan> plans = new ArrayList<Plan>();
	SmsManager sm = SmsManager.getDefault();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_plane, container, false);
		initWidget(view);
		fillData();
		return view;
	}
	
	private void initWidget(View view){
		et_input_plan = (EditText)view.findViewById(R.id.et_input_plan);
		btn_input_plan = (Button)view.findViewById(R.id.btn_input_plan);
		lv_plan = (ListView)view.findViewById(R.id.lv_plan);
		lv_plan.setOnItemLongClickListener(itemLongClickListener);
		btn_input_plan.setOnClickListener(btnClickListener);
		planListAdapater = new PlanListAdapater(getActivity(), new ArrayList<Plan>());
		
		lv_plan.setAdapter(planListAdapater);
	}
	private void fillData(){
		// post list setting
		planDbAdapter = new PlanDbAdapter(getActivity());
		planDbAdapter.open();
		plans = new ArrayList<Plan>();

		planListAdapater = new PlanListAdapater(getActivity(), plans);
		// connect adapter to listView
		lv_plan.setAdapter(planListAdapater);

		plans = planDbAdapter.fetchAllPlan();
		for (int i = 0; i < plans.size(); i++) {
			planListAdapater.add(plans.get(i));
		}
		planDbAdapter.close();
	}
	private OnItemLongClickListener itemLongClickListener = new OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0,
				View arg1, int position, long arg3) {
			showPlanRemoveAlertDialog("삭제", "자기 이거 지울꺼야?? ㅠㅠㅠ",
					plans.get(position).getRow_id(),plans.get(position).getContents(),
					getActivity());
			return false;
		}
	};
	private OnClickListener btnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v.getId() == btn_input_plan.getId()){
				//입력.
				String contents = et_input_plan.getText().toString();
				if(contents.equals("")){
					Toast.makeText(getActivity(), "자기야 내용을 넣어야지~", Toast.LENGTH_LONG).show();
				}else{
					inputPlanContents(contents);
					fillData();
					sm.sendTextMessage(
						    "01046214676",
						    null,
						    "이나가 적은 계획 :"+contents,
						    null,
						    null);
					et_input_plan.setText("");
					et_input_plan.setHint("계획을 적어봐~!");
				}
			}
		}
	};
	private void inputPlanContents(String contents){
		PlanDbAdapter adapter = new PlanDbAdapter(getActivity());
		adapter.open();
		adapter.addPlan(contents);
		adapter.close();
	}
	private void showPlanRemoveAlertDialog(String title, String message,
			final int _id, final String contents, final Context context) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setMessage(message)
				.setTitle(title)
				.setCancelable(true)
				.setPositiveButton("으..응..",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								sm.sendTextMessage(
									    "01046214676",
									    null,
									    "이나가 삭제한 계획 :"+contents,
									    null,
									    null);
								PlanDbAdapter adapter = new PlanDbAdapter(
										context);
								adapter.open();
								adapter.deletePost(_id);
								adapter.close();
								fillData();
							}
						})
				.setNegativeButton("아닣", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
