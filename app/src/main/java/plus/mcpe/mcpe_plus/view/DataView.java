package plus.mcpe.mcpe_plus.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import plus.mcpe.mcpe_plus.ContentActivity;
import plus.mcpe.mcpe_plus.R;
import plus.mcpe.mcpe_plus.model.DataModel;

public class DataView extends RecyclerView {
    public DataModel model;
    private Activity activity;

    public DataView(Activity context, DataModel dataModel) {
        super(context);
        activity = context;
        setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        model = dataModel;
        setAdapter(new DataViewAdapter());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView = null;
        TextView textView = null;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(@NonNull View v) {
                    Intent intent = new Intent(activity, ContentActivity.class);
                    activity.startActivity(intent);
                }
            });
            imageView = (ImageView) view.findViewById(R.id.image);
            textView = (TextView) view.findViewById(R.id.text);
        }

        public void setImage(String uri) {
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)(imageView.getLayoutParams().height * Math.random() * 2 + 0.5)));
            imageLoader.displayImage(uri, imageView, new DisplayImageOptions.Builder().delayBeforeLoading(0).bitmapConfig(Bitmap.Config.RGB_565).build());
        }

        public void setText(CharSequence charSequence) {
            textView.setText(charSequence);
        }
    }

    class DataViewAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = activity.getLayoutInflater().inflate(R.layout.view_card, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setImage(model.getImageUri(position));
            holder.setText(model.getTitle(position));
        }

        @Override
        public int getItemCount() {
            return model.getCount();
        }
    }
}
