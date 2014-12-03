package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.vine.vinemars.R;
import com.vine.vinemars.bus.RegionSelectedEvent;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.smssdk.gui.CountryListView;
import cn.smssdk.gui.GroupListView;
import de.greenrobot.event.EventBus;

/**
 * Created by chengfei on 14/12/2.
 */
public class CountryListFragment extends DialogFragment implements View.OnClickListener, GroupListView.OnItemClickListener {


    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;
    @InjectView(R.id.listview)
    protected CountryListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NORMAL, R.style.Dialog_Signin);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_country_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        toolbar.inflateMenu(R.menu.close);
        toolbar.setOnMenuItemClickListener(
                new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.action_close) {
                            dismiss();
                            return true;
                        }
                        return false;
                    }
                });

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onItemClick(GroupListView parent, View view, int group, int position) {
        String[] selected = (String[]) parent.getAdapter().getItem(group, position);
        EventBus.getDefault().post(new RegionSelectedEvent(selected));
        dismiss();
    }
}
