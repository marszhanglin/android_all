package mars.all.fragment.weixin50;

import mars.all.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MarsFragment01 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) { 
		return inflater.inflate(R.layout.fragment01, container,false);  //没加false参数为什么会报错
	} 
}
