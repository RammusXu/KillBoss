package com.ntut.killboss.menu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ntut.killboss.Constant;
import com.ntut.killboss.R;
import com.ntut.killboss.core.StartGameActivity;
import com.ntut.killboss.setting.GameSetting;
import com.ntut.killboss.setting.ImageAdapter;

public class StageFragment extends Fragment {
	public static int bossInt = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.stage_fragment, container, false);
		findView(v);

		return v;
	}

	private ImageAdapter _bossAdapter;
	private GridView _gridView;

	private void findView(View v) {
		_gridView = (GridView) v
				.findViewById(R.id.stage_fragment_gridView1);

		_bossAdapter = new ImageAdapter(getActivity(), Constant.bossIDs,
				GameSetting._bossEnable);
		_bossAdapter.setCorrectEquip(bossInt);
		_gridView.setAdapter(_bossAdapter);

		_gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (GameSetting._bossEnable[position]) {

					bossInt = position;
					_bossAdapter.setCorrectEquip(bossInt);
					_bossAdapter.notifyDataSetChanged();
				}
				Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT)
						.show();
			}
		});

		ImageButton ibStart = (ImageButton) v
				.findViewById(R.id.stage_fragment_buttonOK);
		ibStart.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						StartGameActivity.class);
				startActivity(intent);

			}
		});
	}
	
	public void updateAdapter(){
		_bossAdapter = new ImageAdapter(getActivity(), Constant.bossIDs,
				GameSetting._bossEnable);
		_bossAdapter.setCorrectEquip(bossInt);
		_gridView.setAdapter(_bossAdapter);
	}
}
