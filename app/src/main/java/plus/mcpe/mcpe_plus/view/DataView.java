package plus.mcpe.mcpe_plus.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import plus.mcpe.mcpe_plus.R;
import plus.mcpe.mcpe_plus.model.DataModel;

public class DataView extends RecyclerView {
    public DataModel model;

    public DataView(Context context) {
        this(context, null);
    }

    public DataView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutManager(new LinearLayoutManager(context));

    }

    public void setDataModel(DataModel dataModel) {
        model = dataModel;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView = null;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text);
        }

        public void setText(CharSequence charSequence) {
            textView.setText(charSequence);
        }
    }

    class DataViewAdapter extends RecyclerView.Adapter<ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext())
                    .inflate(R.layout.view_card, DataView.this, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setText(model.getContent(position));
        }

        @Override
        public int getItemCount() {
            return model.getCount();
        }
    }
}
