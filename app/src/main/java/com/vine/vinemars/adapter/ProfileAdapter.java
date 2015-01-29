package com.vine.vinemars.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.vine.vinemars.MyApplication;
import com.vine.vinemars.R;
import com.vine.vinemars.bus.ProfileHeaderMessuredEvent;
import com.vine.vinemars.domain.User;
import com.vine.vinemars.utils.ImageUtils;
import com.vine.vinemars.view.RoundedAvatarDrawable;

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
    public void onBindViewHolder(final ProfileAdapter.BaseProfileViewHolder viewHolder, int position) {
        int type = getItemViewType(position);

        switch (type) {
            case TYPE_HEADER:
                final HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                final View itemLayout = ((HeaderViewHolder) viewHolder).itemView;
                ((HeaderViewHolder) viewHolder).itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        itemLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                        EventBus.getDefault().post(new ProfileHeaderMessuredEvent(itemLayout.getMeasuredHeight()));
                        return true;
                    }
                });

                Picasso.with(context).load("http://c.hiphotos.baidu.com/image/w%3D230/sign=21ef048bcbea15ce41eee70a86013a25/55e736d12f2eb938b98410fcd7628535e4dd6fd6.jpg").transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {
                        Bitmap bitmap = ImageUtils.convertDrawable2BitmapByCanvas(new RoundedAvatarDrawable(source));
                        source.recycle();
                        return bitmap;
                    }

                    @Override
                    public String key() {
                        return "circle()";
                    }
                }).placeholder(R.drawable.avatar_default).into(headerViewHolder.avatarImage);

//                headerViewHolder.avatarImage.setImageDrawable(new RoundedBitmapDrawable() {
//                });

                Picasso.with(context).load("http://g.hiphotos.baidu.com/image/pic/item/b17eca8065380cd79ff5cf51a244ad34588281a6.jpg").transform(new Transformation() {

                    @Override
                    public Bitmap transform(Bitmap source) {
                        Palette.generateAsync(source, 10, new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                Palette.Swatch swatch =
                                        palette.getVibrantSwatch();
                                if (swatch != null) {
                                    // If we have a vibrant color
                                    // update the title TextView
                                    ((View) (headerViewHolder.nickName.getParent())).setBackgroundColor(
                                            swatch.getRgb());
                                    headerViewHolder.nickName.setTextColor(
                                            swatch.getTitleTextColor());
                                    int bodyTextColor = swatch.getBodyTextColor();
                                    headerViewHolder.signatureText.setTextColor(bodyTextColor);
                                    headerViewHolder.abstractText.setTextColor(bodyTextColor);
                                    headerViewHolder.viewedText.setTextColor(Color.argb(100, Color.red(bodyTextColor), Color.green(bodyTextColor),
                                            Color.blue(bodyTextColor)));
                                }
                            }
                        });
                        return source;
                    }

                    @Override
                    public String key() {
                        return "palette()";
                    }
                }).placeholder(R.drawable.profile_cover_default).into(headerViewHolder.coverImage);
//                Resources res = context.getResources();
//                Bitmap src = BitmapFactory.decodeResource(res, R.drawable.avatar_default);

//                RoundedBitmapDrawable dr =
//                        RoundedBitmapDrawableFactory.create(res, src);
//                dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
//                headerViewHolder.avatarImage.setImageDrawable(dr);
                headerViewHolder.signatureText.setText(user.signature);
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

        @InjectView(R.id.iv_avatar)
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

        @InjectView(R.id.iv_avatar)
        protected ImageView avatarImage;
        @InjectView(R.id.profile_signature)
        protected TextView signatureText;
        @InjectView(R.id.profile_username)
        protected TextView nickName;
        @InjectView(R.id.profile_abstract)
        protected TextView abstractText;
        @InjectView(R.id.profile_viewed)
        protected TextView viewedText;
        @InjectView(R.id.profile_cover)
        protected ImageView coverImage;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

}
