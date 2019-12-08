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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kyluandkylu.android.logiword.Authentication.AccountAuthentication;
import com.kyluandkylu.android.logiword.Game.GameViewModel;
import com.kyluandkylu.android.logiword.R;
import com.kyluandkylu.android.logiword.Retrofit.FriendPair;
import com.kyluandkylu.android.logiword.Retrofit.WebService;

import java.util.List;
import java.util.Stack;

public class FriendListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private FriendListViewModel friendListViewModel;
    private ImageButton imageButtonAcceptInvitation;
    private ImageButton imageButtonRejectInvitation;
    private RelativeLayout relativeLayoutFriendRequest;
    private TextView textViewFriendName;
    private Button buttonAddFriend;
    private EditText editTextFriendName;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friend_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        friendListViewModel = ViewModelProviders.of(this).get(FriendListViewModel.class);

        recyclerView = view.findViewById(R.id.recycle_view_friend);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        friendListViewModel.getFriends().observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(String[] friendModels) {
                adapter = new FriendAdapter(friendModels, new FriendAdapter.OnListItemClickListener() {
                    @Override
                    public void onListItemClick(int clickedItemIndex) {
                        friendListViewModel.removeFriend(clickedItemIndex);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        relativeLayoutFriendRequest = view.findViewById(R.id.relativeLayoutFriendRequest);
        textViewFriendName = view.findViewById(R.id.textViewFriendName);

        friendListViewModel.getFriendRequests().observe(this, new Observer<Stack<String>>() {
            @Override
            public void onChanged(Stack<String> friendRequests) {
                if(friendRequests.isEmpty()){
                    relativeLayoutFriendRequest.setVisibility(View.INVISIBLE);
                }else {
                    relativeLayoutFriendRequest.setVisibility(View.VISIBLE);
                    textViewFriendName.setText(friendListViewModel.getFriendRequests().getValue().peek());
                }
            }
        });

        imageButtonAcceptInvitation = view.findViewById(R.id.imageButtonAcceptFriend);
        imageButtonAcceptInvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendListViewModel.respondToFriendRequest("ACCEPTED");
            }
        });

        imageButtonRejectInvitation = view.findViewById(R.id.imageButtonRejectFriend);
        imageButtonRejectInvitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendListViewModel.respondToFriendRequest("REJECTED");
            }
        });

        editTextFriendName = view.findViewById(R.id.friend_edit_text_view);
        buttonAddFriend = view.findViewById(R.id.friend_add_button);
        buttonAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendListViewModel.sendFriendRequest(editTextFriendName.getText().toString());
                closeSoftKeyboard();
                Toast.makeText(getContext(),"Request to " + editTextFriendName.getText().toString() +" sent",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void closeSoftKeyboard(){

        View view = getActivity().getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



}
