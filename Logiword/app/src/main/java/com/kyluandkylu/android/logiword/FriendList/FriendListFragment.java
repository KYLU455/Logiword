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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kyluandkylu.android.logiword.Game.GameViewModel;
import com.kyluandkylu.android.logiword.R;

import java.util.List;
import java.util.Stack;

public class FriendListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private FriendListViewModel friendListViewModel;
    private ImageButton imageButtonAcceptInvitation;
    private ImageButton imageButtonRejectInvitation;
    private RelativeLayout relativeLayoutFriendRequest;


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

        friendListViewModel.getFriends().observe(this, new Observer<FriendModel[]>() {
            @Override
            public void onChanged(FriendModel[] friendModels) {
                adapter = new FriendAdapter(friendModels, new FriendAdapter.OnListItemClickListener() {
                    @Override
                    public void onListItemClick(int clickedItemIndex) {
                        Log.d("CLICKED", "CLICKED");
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });

        relativeLayoutFriendRequest = view.findViewById(R.id.relativeLayoutFriendRequest);

        friendListViewModel.getFriendRequests().observe(this, new Observer<Stack<FriendRequest>>() {
            @Override
            public void onChanged(Stack<FriendRequest> friendRequests) {
                if(friendRequests.isEmpty()){
                    relativeLayoutFriendRequest.setVisibility(View.INVISIBLE);
                }else {
                    relativeLayoutFriendRequest.setVisibility(View.VISIBLE);
                }
            }
        });

        imageButtonAcceptInvitation = view.findViewById(R.id.imageButtonAcceptFriend);
        imageButtonRejectInvitation = view.findViewById(R.id.imageButtonRejectFriend);


    }

    public void closeSoftKeyboard(){

        View view = getActivity().getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



}
