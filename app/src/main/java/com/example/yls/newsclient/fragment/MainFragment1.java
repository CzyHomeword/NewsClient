package com.example.yls.newsclient.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.yls.newsclient.R;

import java.util.ArrayList;
import java.util.List;

public class MainFragment1 extends BaseFragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_01;
    }
    @Override
    public void initListener () {
    }

    @Override
    public void initData() {
        initViewPager();
    }

    @Override
    public void initView() {
        viewPager = (ViewPager) mRoot.findViewById(R.id.view_pager_02);
        tabLayout = (TabLayout) mRoot.findViewById(R.id.tab_layout);
        initViewPager();
    }


    private void initViewPager() {
        final String[] titles = new String[]{
                "头条", "社会", "科技", "财经", "体育", "汽车"
        };
        final String[] channelId = new String[]{
                "T1348647909107",
                "T1348648037603",
                "T1348649580692",
                "T1348648756099",
                "T1348649079062",
                "T1348654060988",
        };
        final List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            NewsFragment fragment = new NewsFragment();
            fragment.setChannelId(channelId[i]);
            fragments.add(fragment);
        }
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
            public CharSequence getPageTitle(int position){
                return  titles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);

        }


    }
