package com.vine.vinemars.app.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.vine.vinemars.R;
import com.vine.vinemars.domain.Topic;
import com.vine.vinemars.net.MyVolley;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.request.AddTopicRequest;

import butterknife.InjectView;

/**
 * Created by chengfei on 15/1/15.
 */
public class AddTopicFragment extends BaseFragment implements NetworkRequestListener {


    @InjectView(R.id.edit_content)
    protected EditText contentEdit;

    public static AddTopicFragment newInstance() {
        return new AddTopicFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_publish_topic;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.send, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_send) {
            Topic topic = new Topic();
            topic.content = contentEdit.getText().toString();
            topic.thumbnail = "http://f.hiphotos.baidu.com/image/w%3D310/sign=2c7b9686d2a20cf44690f8de46084b0c/e1fe9925bc315c6041517aee8fb1cb13485477ee.jpg";
            topic.picture = "http://f.hiphotos.baidu.com/image/w%3D2048/sign=4454efde74094b36db921ced97f47dd9/e1fe9925bc315c6041517aee8fb1cb13485477ee.jpg";
            MyVolley.getRequestQueue().add(new AddTopicRequest(topic, this));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {

    }
}
