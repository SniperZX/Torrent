package com.rock.software.torrent.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rock.software.torrent.Activity.LiveParserActivity;
import com.rock.software.torrent.Bean.LiveBean;
import com.rock.software.torrent.R;

/**
 * Created by zhaohongru on 2017-02-08.
 * PackageName: com.rock.software.torrent.Adapter.LiveAdapter
 * Description：
 */
public class LiveDetailAdapter extends RecyclerView.Adapter<LiveDetailAdapter.MyViewHolder> {



    LiveBean liveBean;
    Context context;

    public LiveDetailAdapter(LiveBean liveBean, Context context, int pos) {
        this.liveBean = liveBean;
        this.context = context;
        this.pos = pos;
    }

    int pos;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.live_group_item, parent,
                false));
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.tv.setText(liveBean.getRetObject().get(pos).getStations().get(position).getName());
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LiveParserActivity.class);
                    intent.putExtra("id",liveBean.getRetObject().get(pos).getStations().get(position).getID());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return liveBean.getRetObject().get(pos).getStations().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
