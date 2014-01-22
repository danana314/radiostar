package com.radiostar.android.view;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.radiostar.android.R;

public class PlaybackControlFragment extends Fragment implements
		OnClickListener {

	private boolean isPlaying = false;

	public interface OnPlaybackControlledListener {
		public void onPlayPressed();

		public void onStopPressed();

		public void onNextPressed();

		public void onPreviousPressed();
	}

	OnPlaybackControlledListener listener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (OnPlaybackControlledListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnPlaybackControlledListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.radiocontrol, container, false);

		// Attach onClick listeners to all buttons
		int[] controlButtonIDs = { R.id.play, R.id.stop, R.id.next,
				R.id.previous };
		for (int controlID : controlButtonIDs) {
			ImageButton ib = (ImageButton) v.findViewById(controlID);
			ib.setOnClickListener(this);
		}
		return v;
	}

	@Override
	public void onStart() {
		super.onStart();
		// set initial play/stop view state
		setPlaying(isPlaying);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			listener.onPlayPressed();
			setPlaying(true);
			Log.v("onClick", "Play pressed");
			break;
		case R.id.stop:
			listener.onStopPressed();
			setPlaying(false);
			Log.v("onClick", "Stop pressed");
			break;
		case R.id.next:
			listener.onNextPressed();
			Log.v("onClick", "Next pressed");
			break;
		case R.id.previous:
			listener.onPreviousPressed();
			Log.v("onClick", "Previous pressed");
			break;
		}
	}

	public void setPlaying(boolean isPlaying) {
		ImageButton ib;
		if (isPlaying) {
			// Hide play button
			ib = (ImageButton) getView().findViewById(R.id.play);
			ib.setVisibility(View.INVISIBLE);

			// Make prev, stop, next buttons visible
			ib = (ImageButton) getView().findViewById(R.id.previous);
			ib.setVisibility(View.VISIBLE);
			
			ib = (ImageButton) getView().findViewById(R.id.stop);
			ib.setVisibility(View.VISIBLE);
			
			ib = (ImageButton) getView().findViewById(R.id.next);
			ib.setVisibility(View.VISIBLE);
			
			showLoadingStopButton(true);	// Indicate speech is loading
			
			Log.v("PlaybackControlFragment", "setPlaying: playing");
		} else {
			// Show play button
			ib = (ImageButton) getView().findViewById(R.id.play);
			ib.setVisibility(View.VISIBLE);
			
			// Hide prev, stop, and next buttons
			ib = (ImageButton) getView().findViewById(R.id.previous);
			ib.setVisibility(View.INVISIBLE);
			
			ib = (ImageButton) getView().findViewById(R.id.stop);
			ib.setVisibility(View.INVISIBLE);
			
			ib = (ImageButton) getView().findViewById(R.id.next);
			ib.setVisibility(View.INVISIBLE);
			
			Log.v("PlaybackControlFragment", "setPlaying: stopped");
		}
		this.isPlaying = (isPlaying);
	}
	
	public void showLoadingStopButton(boolean showLoadingStop) {
		ImageButton ib = (ImageButton) getView().findViewById(R.id.stop);
		
		if (showLoadingStop) {
			ib.setBackgroundResource(R.drawable.stop_loading_animation);
			
			// Get the background, which has been compiled to an AnimationDrawable object.
			 AnimationDrawable frameAnimation = (AnimationDrawable) ib.getBackground();

			 // Start the animation (looped playback by default).
			 frameAnimation.start();
		} else {
			// Get the background, which has been compiled to an AnimationDrawable object.
			 AnimationDrawable frameAnimation = (AnimationDrawable) ib.getBackground();

			 // Start the animation (looped playback by default).
			 frameAnimation.stop();
			 
			ib.setBackgroundResource(R.drawable.control_stop);
		}		
	}
}
