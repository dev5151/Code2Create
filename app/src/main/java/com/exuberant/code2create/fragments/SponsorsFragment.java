package com.exuberant.code2create.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exuberant.code2create.R;
import com.exuberant.code2create.adapters.SponsorAdpater;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class SponsorsFragment extends Fragment {

    FirebaseDatabase mDatabase;
    DatabaseReference sponsorReference;
    RecyclerView sponsorRecyclerView;
    List<String> sponsorLinkList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        sponsorRecyclerView = view.findViewById(R.id.rv_sponsor_list);
        mDatabase = FirebaseDatabase.getInstance();
        sponsorReference = mDatabase.getReference().child("sponsors");

        sponsorReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sponsorData : dataSnapshot.getChildren()){
                    sponsorLinkList.add((String) sponsorData.getValue());
                }
                SponsorAdpater adpater = new SponsorAdpater(sponsorLinkList);
                sponsorRecyclerView.setAdapter(adpater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}
