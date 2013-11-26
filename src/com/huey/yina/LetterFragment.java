package com.huey.yina;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LetterFragment  extends Fragment{
	private TextView tv_letter_contents1;
	private TextView tv_letter_contents2;
	private TextView tv_letter_contents3;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_letter, container, false);
		
		initWidget(view);
		tv_letter_contents1.setText(" 이나야 안녕~~?\n 우리가 벌써!! 만난지 100일이야 호호. 어찌보면 정말 긴 시간이고 어떻게 보니 엄청 금방지나간거 같기도해ㅎㅎ \n 첫만남 부터..");
		tv_letter_contents2.setText("나의 생일 까지 ㅎㅎ");
		tv_letter_contents3.setText("나의 옆에서 큰 힘이 되어 줘서 너무 고맙고 ㅎㅎ  가끔 서로에게 아쉽거나 기대는 부분이 많아서 ㅠ 서운할 때가 많았지?! 당신은 나를 사랑해서, 그리고 나는 너를 사랑해서 그런가봐 ㅎㅎ 매일 아침 눈 뜰 때 부터 잠자기 전까지 항상 네 생각이 내 머리에 대부분을 차지하고 있어! 고만좀 하게 해줘 ㅠ ㅋㅋㅋ 일을 할 수 가 없자나!!\n 자기, 여보, 이나야 아직은 많이 부족하고 넌 나한테 너무 과분하지만 내가 더 열심히해서 더 노력해서 꼭 네가 안심하고 기대고 위로 받을 수 있는 남자친구가 또는 머 먼미래에는 흐흐흫흐 그런 너의 편이 되어 드릴게요. 지금까지 우리가 겪은 굴곡들은 앞으로 우리사이를 더 가깝게 해주는 것이라고 느껴요 ㅎ 뭐.. 앞으로 ㅎㅎ 더 큰 시련이나 힘든일이 올지도 모르지만!! 지금까지 잘해왔지? ㅎㅎ 더 잘할 수 있어! 그럴 때마다 우리 같이 대화하고 얘기하면서 더욱 더 많이 사랑하고 아껴주는 그런 연인♥이 됩시다. 사랑해요 여보 백일동안 수고많았고 ㅋㅋㅋㅋㅋ 앞으로도 잘 부탁드립니다♥");      
		
		return view;
	}
	
	private void initWidget(View view){
		tv_letter_contents1 = (TextView)view.findViewById(R.id.tv_letter_contents1);
		tv_letter_contents2 = (TextView)view.findViewById(R.id.tv_letter_contents2);
		tv_letter_contents3 = (TextView)view.findViewById(R.id.tv_letter_contents3);
	}
}

