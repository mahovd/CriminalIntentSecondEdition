package com.mahovd.bignerdranch.criminalintentsecondedition;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

/**
 * Created by dmitriymakhov on 21/06/2017.
 */

public class CrimeListFragment  extends Fragment{

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);

        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        updateUI();

        return view;
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);

        }

        public void bind(Crime crime){

            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());

            String formattedDate = DateFormat.format("E, MMM dd, yyyy",mCrime.getDate()).toString();
            mDateTextView.setText(formattedDate);


            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);

        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(),mCrime.getTitle()+" clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }
    }

    private class CrimeHolderWithPoliceOption extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Button mCallPoliceButton;

        private Crime mCrime;

        public CrimeHolderWithPoliceOption(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.lst_item_crime_extended, parent, false));


            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mCallPoliceButton = (Button) itemView.findViewById(R.id.crime_call_police_button);

            mCallPoliceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,"We're calling to the police", BaseTransientBottomBar.LENGTH_SHORT).show();
                }
            });

        }

        public void bind(Crime crime){

            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());

            String formattedDate = DateFormat.format("E, MMM dd, yyyy",mCrime.getDate()).toString();
            mDateTextView.setText(formattedDate);

        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(getActivity(),mCrime.getTitle()+" clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }

    }

    private class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private List<Crime> mCrimes;


        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            RecyclerView.ViewHolder holder;

            if (viewType==1){
                holder = new CrimeHolder(layoutInflater,parent);
            } else {
                holder = new CrimeHolderWithPoliceOption(layoutInflater,parent);
            }

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            Crime crime = mCrimes.get(position);

            if (holder instanceof CrimeHolder) {
                ((CrimeHolder) holder).bind(crime);
            }else if (holder instanceof CrimeHolderWithPoliceOption){
                ((CrimeHolderWithPoliceOption) holder).bind(crime);
            }

        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        //TODO:Determine which view to load: 1) With "call to police" option or without it
        @Override
        public int getItemViewType(int position) {

            int viewType = 1;

            //if the crime wasn't solved and need "Call to the police" option we change the ViewHolder
            if (mCrimes.get(position).isRequiresPolicy() && !mCrimes.get(position).isSolved()){
                viewType = 2; //requiresPolicyCall
            }

            return viewType;
        }

    }

    private void updateUI(){

        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);

    }


}
