/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.radiostar.android.view;

import android.app.Activity;
import android.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HeadlinesFragment extends ListFragment {
    OnHeadlineSelectedListener listener;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /** Called by HeadlinesFragment when a list item is selected */
        public void onArticleSelected(int position);
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            listener = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
    
    public void setDataSource(String[] titles) {
    	// Create an array adapter for the list view, using the Ipsum headlines array
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, titles));
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	// Set the item as checked to be highlighted
        getListView().setItemChecked(position, true);
        
    	// Notify the parent activity of selected item
        listener.onArticleSelected(position);
        
        
    }    
    
    public int getSelectedPosition() {
    	return getListView().getCheckedItemPosition();
    }
    
    public boolean selectNext() {
    	int posNext = getListView().getCheckedItemPosition() + 1;
    	int count = getListAdapter().getCount();
    	if (posNext < count) {
    		getListView().setItemChecked(posNext, true);
    		listener.onArticleSelected(posNext);
    		return true;
    	} else {
    		getListView().setItemChecked(posNext - 1, false);
    		return false;
    	}
    }
    
    public boolean selectPrevious() {
    	int posPrev = getListView().getCheckedItemPosition() - 1;
    	if (posPrev >= 0) {
    		getListView().setItemChecked(posPrev, true);
    		listener.onArticleSelected(posPrev);
    		return true;
    	} else {
    		getListView().setItemChecked(posPrev + 1, false);
    		return false;
    	}
    }
}