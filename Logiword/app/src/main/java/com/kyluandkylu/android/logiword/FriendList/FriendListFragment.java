package com.kyluandkylu.android.logiword.FriendList;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyluandkylu.android.logiword.R;

import java.util.List;

public class FriendListFragment extends Fragment {

    private FriendListViewModel mViewModel;
    private  RecyclerView recyclerView;
    private FriendAdapter friendAdapter;
    private Button addFriendButton;
    private EditText getFriendEditText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.friend_list_fragment, container, false);

        addFriendButton = v.findViewById(R.id.friend_add_button);
        getFriendEditText = v.findViewById(R.id.friend_edit_text_view);

        recyclerView = v.findViewById(R.id.recycle_view_friend);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        friendAdapter = new FriendAdapter();
        recyclerView.setAdapter(friendAdapter);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mViewModel = ViewModelProviders.of(this).get(FriendListViewModel.class);
        mViewModel.getAllFriend().observe(this, new Observer<List<FriendModel>>() {
            @Override
            public void onChanged(List<FriendModel> friendModels) {
                friendAdapter.setFriends(friendModels);
            }
        });

        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String friendName = getFriendEditText.getText().toString();
                addFriend(friendName);
                getFriendEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                closeSoftKeyboard();

            }
        });

    }


    public void addFriend(String name){
        mViewModel.addFriend(name);
        Toast.makeText(getActivity(), "Friend has been added", Toast.LENGTH_SHORT).show();

    }

    public void closeSoftKeyboard(){

        View view = getActivity().getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
