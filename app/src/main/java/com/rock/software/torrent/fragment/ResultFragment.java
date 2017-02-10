package com.rock.software.torrent.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rock.software.torrent.App;
import com.rock.software.torrent.Bean.ResultBean;
import com.rock.software.torrent.R;
import com.sniper.yunbo.ProgressActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;




public class ResultFragment extends Fragment {

    private static final String ARG_PARAM1 = "index";
    private static final String ARG_PARAM2 = "key";
    List<ResultBean> list;
    private int index;
    private String key;
    private View view;
    private TextView tv;
    private ListView listView;
    private int lastItem;
    private MyAdapter adapter;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == -1) {
                Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(getActivity(), "获取成功，正在解析", Toast.LENGTH_SHORT).show();
                String result = (String) msg.obj;
//                if (index == 0) {

                    try {
                        Class c = Class.forName(parsers[index]);
                        Object in = c.newInstance();
                        Method m = c.getDeclaredMethod("parser",String.class);
                        List<ResultBean> tempList = (List<ResultBean>) m.invoke(in,result);
                        list.addAll(tempList);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (java.lang.InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

//                    List<ResultBean> tempList = new XHubParser().parser(result);
//                    list.addAll(tempList);
//                }  else if (index == 1) {
//                    List<ResultBean> tempList = new BTHaveParser().parser(result);
//                    list.addAll(tempList);
//                } else if (index == 2) {
//                    List<ResultBean> tempList = new BTFuliParser().parser(result);
//                    list.addAll(tempList);
//                } else if (index == 3) {
//                    List<ResultBean> tempList = new BTSoSoParser().parser(result);
//                    list.addAll(tempList);
//                } else if (index == 4) {
//                    List<ResultBean> tempList = new BT1280Parser().parser(result);
//                    list.addAll(tempList);
//                }else if(index == 5){
//                    List<ResultBean> tempList = new BTioParser().parser(result);
//                    list.addAll(tempList);
//                }
                adapter.notifyDataSetChanged();
            }
            super.handleMessage(msg);
        }
    };
    private int page = 1;
    private String[] websites;
    private String[] parsers;


    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(int index1, String key1) {
        ResultFragment resultFragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index1);
        args.putString(ARG_PARAM2, key1);
        resultFragment.setArguments(args);
        return resultFragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = getArguments().getInt(ARG_PARAM1);
        key = getArguments().getString(ARG_PARAM2);
        websites = getResources().getStringArray(R.array.Websites);
        parsers = getResources().getStringArray(R.array.parsers);
        list = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_result, container, false);
//            tv = (TextView) view.findViewById(R.id.tv);
            listView = (ListView) view.findViewById(R.id.listView);
            adapter = new MyAdapter(list);
            listView.setAdapter(adapter);
//            tv.setText("key:"+key+"index:"+index);

            getData(String.format(websites[index], key, page));
              //  OkHttpManager.OKdoGet(String.format(websites[index], key, page), handler);
        } else {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                lastItem = firstVisibleItem + visibleItemCount;
                // TODO Auto-generated method stub
            }

            public void onScrollStateChanged(AbsListView view,
                                             int scrollState) {
                if (lastItem == adapter.getCount() && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    page++;
               //     OkHttpManager.OKdoGet(String.format(websites[index], key, page), handler);
                    getData(String.format(websites[index], key, page));
                }
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void getData(String url){

        StringRequest stringRequest  = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Message msg = new Message();
                msg.what =1;
                msg.obj = s;
                handler.sendMessage(msg);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), "出错："+volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        App.getInstance().getRequestQueue().add(stringRequest);

    }





    /**
     * 这个方法适合不知道具体的class，启动主class
     */
    private void openApp(String packageName) {
        PackageInfo pi = null;
        PackageManager pm = getActivity().getPackageManager();
        try {
            pi = pm.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(pi.packageName);

        List<ResolveInfo> apps = pm.queryIntentActivities(resolveIntent, 0);

        ResolveInfo ri = apps.iterator().next();
        if (ri != null) {
            String packName = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;
//            className = "com.ylmf.androidclient.lixian.DiskOfflineTaskActivity";
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packName, className);
            intent.setComponent(cn);
            startActivity(intent);
        }
    }

    public void openApp(String packageName, String className) {
        Intent intent = new Intent();
//        intent.setClassName(packageName, className);

        //second method
        intent.setComponent(new ComponentName(
                packageName,
                className
        ));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //传递数据
//        Bundle bundle = new Bundle();
//        bundle.putString("msg", "this message is from project B ");
//        intent.putExtras(bundle);

//        intent.putExtra("pid", android.os.Process.myPid());

        startActivity(intent);

    }


    public class MyAdapter extends BaseAdapter {
        List<ResultBean> results;

        public MyAdapter(List<ResultBean> results) {
            this.results = results;
        }


        @Override
        public int getCount() {
            return results.size();
        }

        @Override
        public Object getItem(int position) {
            return results.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(getActivity()).inflate(R.layout.result_item, null);
                viewHolder.name = (TextView) view.findViewById(R.id.name);
                viewHolder.magnet = (TextView) view.findViewById(R.id.magnet);
                viewHolder.detail = (TextView) view.findViewById(R.id.detail);
                viewHolder.imageView = (ImageView) view.findViewById(R.id.img);
                viewHolder.yunbo = (Button) view.findViewById(R.id.play);
                viewHolder.copy = (Button) view.findViewById(R.id.copy);
                viewHolder.router = (Button) view.findViewById(R.id.router);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.name.setText("" + results.get(position).name);
            viewHolder.magnet.setText("" + results.get(position).magnet);
            viewHolder.detail.setText("" + results.get(position).detail);
            viewHolder.yunbo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ProgressActivity.class);
                    intent.putExtra("magnet", results.get(position).magnet);
                    startActivity(intent);
                }
            });
            viewHolder.router.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager c = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
//                    c.setText(results.get(position).magnet);//设置Clipboard 的内容
                    c.setPrimaryClip(ClipData.newPlainText("download", results.get(position).magnet));
                    openApp("com.xiaomi.router");
                }
            });

            viewHolder.copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClipboardManager c = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
//                    c.setText(results.get(position).magnet);//设置Clipboard 的内容
                    c.setPrimaryClip(ClipData.newPlainText("download", results.get(position).magnet));
//                    openApp("com.ylmf.androidclient");
//                   openApp("com.ylmf.androidclient", "com.ylmf.androidclient.lixian.DiskOfflineTaskActivity");
                    Toast.makeText(getContext(), "复制完成", Toast.LENGTH_SHORT).show();
                }
            });


            return view;
        }
    }

    class ViewHolder {
        public TextView name, magnet, detail;
        public Button yunbo, copy,router;
        public ImageView imageView;

    }

}
