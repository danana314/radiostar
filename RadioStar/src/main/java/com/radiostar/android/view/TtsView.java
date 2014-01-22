package com.radiostar.android.view;

import com.radiostar.android.R;
import com.radiostar.android.view.HeadlinesFragment.OnHeadlineSelectedListener;
import com.radiostar.android.view.PlaybackControlFragment.OnPlaybackControlledListener;
import com.radiostar.android.view.VocalizerFragment.OnSpeakingListener;
import com.radiostar.android.util.Ipsum;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView;

public class TtsView extends Activity implements OnPlaybackControlledListener,
		OnSpeakingListener, OnHeadlineSelectedListener {
	private VocalizerFragment _vocalizerFragment;

	public TtsView() {
		super();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setVolumeControlStream(AudioManager.STREAM_MUSIC); // So that the 'Media
															// Volume' applies
															// to this activity
		setContentView(R.layout.tts);
		
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);

		FragmentManager fm = getFragmentManager();
		_vocalizerFragment = (VocalizerFragment) fm
				.findFragmentByTag("vocalizer");

		// If non-null, then it is currently being retained across a
		// configuration change
		if (_vocalizerFragment == null) {
			_vocalizerFragment = new VocalizerFragment();
			fm.beginTransaction().add(_vocalizerFragment, "vocalizer").commit();
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		// Load data source
		HeadlinesFragment headlinesFrag = (HeadlinesFragment) getFragmentManager()
				.findFragmentById(R.id.headlinesList);
		if (headlinesFrag != null) {
			headlinesFrag.setDataSource(Ipsum.getTitles());
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// Play article at position
	private void play(int position) {
		PlaybackControlFragment controlFrag = (PlaybackControlFragment) getFragmentManager()
				.findFragmentById(R.id.playbackcontrol);
		if (controlFrag != null) {
			controlFrag.setPlaying(true);
		}

		String text = Ipsum.getTitle(position) + "\n\n"
				+ Ipsum.getDescription(position);
		_vocalizerFragment.speak(text);
	}

	@Override
	public void onArticleSelected(int position) {
		// Stop current article
		_vocalizerFragment.cancel();

		play(position);
	}

	@Override
	public void onPlayPressed() {
		HeadlinesFragment headlinesFrag = (HeadlinesFragment) getFragmentManager()
				.findFragmentById(R.id.headlinesList);
		if (headlinesFrag != null) {
			int currentPos = headlinesFrag.getSelectedPosition();
			if (currentPos != AdapterView.INVALID_POSITION) {
				play(currentPos);
			} else {
				headlinesFrag.selectNext();
			}
		}
	}

	@Override
	public void onStopPressed() {
		// Stop current article
		_vocalizerFragment.cancel();
	}

	@Override
	public void onNextPressed() {
		// Stop current article
		_vocalizerFragment.cancel();

		// Select next article
		HeadlinesFragment headlinesFrag = (HeadlinesFragment) getFragmentManager()
				.findFragmentById(R.id.headlinesList);
		if (headlinesFrag != null) {
			headlinesFrag.selectNext();
		}
	}

	@Override
	public void onPreviousPressed() {
		// Stop current article
		_vocalizerFragment.cancel();

		// Select previous article
		HeadlinesFragment headlinesFrag = (HeadlinesFragment) getFragmentManager()
				.findFragmentById(R.id.headlinesList);
		if (headlinesFrag != null) {
			headlinesFrag.selectPrevious();
		}
	}

	@Override
	public void onSpeakingBegin() {
		PlaybackControlFragment controlFrag = (PlaybackControlFragment) getFragmentManager()
				.findFragmentById(R.id.playbackcontrol);
		if (controlFrag != null) {
			controlFrag.showLoadingStopButton(false);
		}
		Log.v("TTS", "onSpeakingBegin: " + controlFrag.toString());
	}

	@Override
	public void onSpeakingDone() {
		PlaybackControlFragment controlFrag = (PlaybackControlFragment) getFragmentManager()
				.findFragmentById(R.id.playbackcontrol);
		if (controlFrag != null) {
			controlFrag.setPlaying(false);
		}
		Log.v("TTS", "onSpeakingDone: " + controlFrag.toString());
	}
}
