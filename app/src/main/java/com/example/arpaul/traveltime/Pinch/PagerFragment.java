package com.example.arpaul.traveltime.Pinch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arpaul.traveltime.PagerActivity;
import com.example.arpaul.traveltime.R;

/**
 * Created by ARPaul on 12-03-2016.
 */
public class PagerFragment extends Fragment {

    public static final int NUM_PAGES = 4;
    public ViewPager mPagerHandler;
    private myPageAdapter mPagerAdapter;
    private MainScreenFragment[] viewFragments = new MainScreenFragment[4];
    private String[] pageTitle = {"Item 1","Item 2","Item 3","Item 4","Item 5"};
    private LinearLayout llButton;
    private Button btnRed,btnBlue,btnGreen;
    private TextView tvItemNumber;
    private RecyclerView rvItems;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.pager_fragment, container, false);
        mPagerHandler = (ViewPager) rootView.findViewById(R.id.pager);
        mPagerAdapter = new myPageAdapter(getChildFragmentManager());
        for (int i = 0;i < NUM_PAGES;i++)
        {
            viewFragments[i] = new MainScreenFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("fragment_number", i);
            viewFragments[i].setArguments(bundle);
        }
        mPagerHandler.setAdapter(mPagerAdapter);
        mPagerHandler.setCurrentItem(PagerActivity.current_fragment);

        llButton = (LinearLayout) rootView.findViewById(R.id.llButton);
        btnRed = (Button) rootView.findViewById(R.id.btnRed);
        btnBlue = (Button) rootView.findViewById(R.id.btnBlue);
        btnGreen = (Button) rootView.findViewById(R.id.btnGreen);

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llButton.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llButton.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
            }
        });

        tvItemNumber = (TextView) rootView.findViewById(R.id.tvItemNumber);
        rvItems = (RecyclerView) rootView.findViewById(R.id.rvItems);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        ItemAdapter trailerAdapter = new ItemAdapter(getActivity(),pageTitle);
        rvItems.setAdapter(trailerAdapter);
        rvItems.setLayoutManager(layoutManager);

        return rootView;
    }

    private class myPageAdapter extends FragmentStatePagerAdapter
    {
        @Override
        public Fragment getItem(int i)
        {
            return viewFragments[i];
        }

        @Override
        public int getCount()
        {
            return NUM_PAGES;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public myPageAdapter(FragmentManager fm)
        {
            super(fm);
        }
        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position)
        {
            return pageTitle[position];
        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

        private Context context;
        private String[] pageTitle;

        public ItemAdapter(Context context, String[] pageTitle) {
            this.context = context;
            this.pageTitle = pageTitle;
        }

        public class ItemHolder extends RecyclerView.ViewHolder {

            View view_Cell;
            TextView tvItem;
            public ItemHolder(View view) {
                super(view);
                view_Cell   =    view;
                tvItem      =   (TextView) view.findViewById(R.id.tvItem);
            }
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = LayoutInflater.from(context).inflate(R.layout.item_text,parent,false);
            ItemHolder rvHolder = new ItemHolder(convertView);
            return rvHolder;
        }

        @Override
        public int getItemCount() {
            if(pageTitle != null)
                return pageTitle.length;
            return 0;
        }

        @Override
        public void onBindViewHolder(final ItemHolder holder, final int position) {
            final String  itemNAME = pageTitle[position];

            holder.tvItem.setText(itemNAME);

            holder.view_Cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvItemNumber.setText(itemNAME);
                }
            });
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }
}
