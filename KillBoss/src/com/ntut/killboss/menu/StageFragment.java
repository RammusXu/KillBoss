package com.ntut.killboss.menu;

import com.ntut.killboss.MainActivity;
import com.ntut.killboss.R;
import com.ntut.killboss.core.StartGameActivity;
import com.ntut.killboss.setting.ImageAdapter;

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

public class StageFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.stage_fragment, container, false);
		findView(v);

		return v;
	}

	private void findView(View v) {
		GridView gridView = (GridView) v
				.findViewById(R.id.stage_fragment_gridView1);

		gridView.setAdapter(new ImageAdapter(getActivity()));

		gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
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
}
