package com.vine.vinemars.net.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.vine.vinemars.domain.Topic;
import com.vine.vinemars.net.AppHead;
import com.vine.vinemars.net.HOpCodeEx;
import com.vine.vinemars.net.NetworkRequestListener;
import com.vine.vinemars.net.ParseError;
import com.vine.vinemars.net.pb.SocialMessage;

/**
 * Created by chengfei on 15/1/16.
 */
public class AddTopicRequest extends BaseRequest<Topic> {

    private Topic topic;
    public AddTopicRequest(Topic topic, NetworkRequestListener listener) {
        super(HOpCodeEx.AddTopic, listener);
        this.topic = topic;
    }

    @Override
    protected Topic parse(AppHead appHead, NetworkResponse response) throws ParseError {
//        try {
//            SocialMessage.AddTopic ret = SocialMessage.AddTopic.parseFrom(response.data);
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        SocialMessage.AddTopic.Builder builder = SocialMessage.AddTopic.newBuilder();
        builder.setContent(topic.content);
        builder.setThumbnail(topic.thumbnail);
        if (topic.localPicture != null) {
            builder.setLocalPicture(topic.localPicture);
        }
        return builder.build().toByteArray();
    }
}
