package com.vine.vinemars.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;
import com.vine.vinemars.bus.ProfileHeaderMessuredEvent;
import com.vine.vinemars.domain.User;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import de.greenrobot.event.EventBus;

/**
 * Created by chengfei on 14/11/14.
 */
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.BaseProfileViewHolder> {


    private Context context;
    private User user;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_BASIC_INFO = 4;
    private static final int TYPE_WORK = 5;
    private static final int TYPE_EDUCATION = 10;
    private static final int TYPE_PLACE = 11;
    private static final int TYPE_LINKS = 20;
    private static final int TYPE_SET[] = new int[]{TYPE_HEADER, TYPE_BASIC_INFO,/* TYPE_WORK,
            TYPE_EDUCATION, TYPE_PLACE, TYPE_LINKS */};

    private static final int TYPE_COUNT = TYPE_SET.length;
    private LayoutInflater layoutInflater;

    public ProfileAdapter(User user) {
        this.user = user;
        this.context = MyApplication.get();
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        return TYPE_SET[position];
    }

    @Override
    public ProfileAdapter.BaseProfileViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            case TYPE_BASIC_INFO:
//                return new BasicInfoViewHolder(layoutInflater.inflate(R.layout.item_profile_basic_information, viewGroup, false));
            case TYPE_WORK:
                return new BasicInfoViewHolder(layoutInflater.inflate(R.layout.item_profile_basic_information, viewGroup, false));
            case TYPE_EDUCATION:
                return new EducationViewHolder(layoutInflater.inflate(R.layout.item_profile_education, viewGroup, false));
            case TYPE_LINKS:
                return new LinksViewHolder(layoutInflater.inflate(R.layout.item_profile_links, viewGroup, false));
            case TYPE_PLACE:
                return new PlacesViewHolder(layoutInflater.inflate(R.layout.item_profile_places, viewGroup, false));
            case TYPE_HEADER:
                return new HeaderViewHolder(layoutInflater.inflate(R.layout.item_profile_header, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.BaseProfileViewHolder viewHolder, int position) {
        int type = getItemViewType(position);

        switch (type) {
            case TYPE_HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                final View itemLayout = ((HeaderViewHolder) viewHolder).itemView;
                ((HeaderViewHolder) viewHolder).itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        itemLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                        EventBus.getDefault().post(new ProfileHeaderMessuredEvent(itemLayout.getMeasuredHeight()));
                        return true;
                    }
                });
//                Picasso.with(context).load(R.drawable.avatar_default).transform(new Transformation() {
//                    @Override
//                    public Bitmap transform(Bitmap source) {
//                        return ImageUtils.convertDrawable2BitmapByCanvas(new RoundedAvatarDrawable(source));
//                    }
//
//                    @Override
//                    public String key() {
//                        return ;
//                    }
//                }).into(headerViewHolder.avatarImage);

//                headerViewHolder.avatarImage.setImageDrawable(new RoundedBitmapDrawable() {
//                });
                Resources res = context.getResources();
                Bitmap src = BitmapFactory.decodeResource(res, R.drawable.avatar_default);
                RoundedBitmapDrawable dr =
                        RoundedBitmapDrawableFactory.create(res, src);
                dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
                headerViewHolder.avatarImage.setImageDrawable(dr);
                headerViewHolder.signatrueText.setText(user.signature);
                headerViewHolder.nickName.setText(user.nickname);
                break;
            case TYPE_BASIC_INFO:
                viewHolder.titleText.setText(R.string.basic_info);
                BasicInfoViewHolder basicInfoViewHolder = (BasicInfoViewHolder) viewHolder;
                basicInfoViewHolder.genderText.setText(user.getGender());
                basicInfoViewHolder.editGenderButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                break;
            case TYPE_WORK:
                viewHolder.titleText.setText(R.string.work);
                break;
            case TYPE_EDUCATION:
                viewHolder.titleText.setText(R.string.education);
                break;
            case TYPE_LINKS:
                viewHolder.titleText.setText(R.string.links);
                break;
            case TYPE_PLACE:
                viewHolder.titleText.setText(R.string.places);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return TYPE_COUNT;
    }

    public static class BasicInfoViewHolder extends BaseProfileViewHolder {

        @InjectView(R.id.item_profile_birthday)
        public TextView birthdayText;
        @InjectView(R.id.item_profile_gender)
        public TextView genderText;

        @InjectView(R.id.item_profile_gender_edit)
        public ImageButton editGenderButton;

        public BasicInfoViewHolder(View itemView) {
            super(itemView);

        }
    }

    public static class BaseProfileViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_profile_title)
        @Optional
        public TextView titleText;

        public BaseProfileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

        }

    }

    public static final class LinksViewHolder extends BaseProfileViewHolder {
        public LinksViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static final class PlacesViewHolder extends BaseProfileViewHolder {

        public PlacesViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static final class ProfileHeaderViewHolder extends BaseProfileViewHolder {

        @InjectView(R.id.avatar)
        protected ImageView avatarImage;
        @InjectView(R.id.profile_cover)
        protected ImageView coverImage;

        public ProfileHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class EducationViewHolder extends BaseProfileViewHolder {

        public EducationViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class HeaderViewHolder extends BaseProfileViewHolder {

        @InjectView(R.id.avatar)
        protected ImageView avatarImage;
        @InjectView(R.id.profile_signature)
        protected TextView signatrueText;
        @InjectView(R.id.profile_username)
        protected TextView nickName;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

}
