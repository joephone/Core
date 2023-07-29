package com.transcendence.core.publicModule.article.adapter;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.transcendence.core.R;
import com.transcendence.core.base.adapter.BaseAdapter;
import com.transcendence.core.base.app.CoreApp;
import com.transcendence.core.publicModule.article.bean.ArticleBean;
import com.transcendence.core.publicModule.db.DatabaseHelper;
import com.transcendence.core.publicModule.smartrefreshlayout.widget.CommonBaseViewHolder;
import com.transcendence.core.publicModule.web.act.AgentWebAc;
import com.transcendence.core.utils.glide.GlideWrapper;
import com.transcendence.core.utils.text.StringUtils;
import com.transcendence.core.publicModule.smartrefreshlayout.listener.OnClickListener2;
//import com.transcendence.core.publicModule.web.act.WanWebActivity;

/**
 * @Author Joephone on 2022/9/5 0005 上午 11:10
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 * @Edition 1.0
 * @EditionHistory
 */
public class ArticleAdapter extends BaseAdapter<ArticleBean> {

    private static DatabaseHelper mDBhepler;
    public ArticleAdapter() {
        super(R.layout.rv_item_article);
        mDBhepler = CoreApp.getDBhepler();
    }

    @Override
    protected void convert(@NonNull CommonBaseViewHolder helper, ArticleBean item) {
        bindArticle(helper.itemView, item);
    }

    public static void bindArticle(View view, ArticleBean item) {
//        HtmlCacheManager.INSTANCE.submit(item.getLink());
        TextView tv_top = view.findViewById(R.id.tv_top);
        TextView tv_new = view.findViewById(R.id.tv_new);
        TextView tv_author = view.findViewById(R.id.tv_author);
        TextView tv_tag = view.findViewById(R.id.tv_tag);
        TextView tv_time = view.findViewById(R.id.tv_time);
        ImageView iv_img = view.findViewById(R.id.iv_img);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_desc = view.findViewById(R.id.tv_desc);
        TextView tv_chapter_name = view.findViewById(R.id.tv_chapter_name);
//        CollectView cv_collect = view.findViewById(R.id.cv_collect);
        if (item.isTop()) {
            tv_top.setVisibility(View.VISIBLE);
        } else {
            tv_top.setVisibility(View.GONE);
        }
        if (item.isFresh()) {
            tv_new.setVisibility(View.VISIBLE);
        } else {
            tv_new.setVisibility(View.GONE);
        }
        tv_author.setText(item.getAuthor());
        if (item.getTags() != null && item.getTags().size() > 0) {
            tv_tag.setText(item.getTags().get(0).getName());
            tv_tag.setVisibility(View.VISIBLE);
            tv_tag.setOnClickListener(new OnClickListener2() {
                @Override
                public void onClick2(View v) {
//                    KnowledgeArticleActivity.start(v.getContext(), item.getTags().get(0));
                }
            });
        } else {
            tv_tag.setVisibility(View.GONE);
        }
        tv_time.setText(item.getNiceDate());
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            GlideWrapper.getInstance().loadImageFromUrl(item.getEnvelopePic(),iv_img);
            iv_img.setVisibility(View.VISIBLE);
        } else {
            iv_img.setVisibility(View.GONE);
        }
        tv_title.setText(Html.fromHtml(item.getTitle()));
        if (TextUtils.isEmpty(item.getDesc())) {
            tv_desc.setVisibility(View.GONE);
            tv_title.setSingleLine(false);
        } else {
            tv_desc.setVisibility(View.VISIBLE);
            tv_title.setSingleLine(true);
            String desc = Html.fromHtml(item.getDesc()).toString();
            desc = StringUtils.removeAllBank(desc, 2);
            tv_desc.setText(desc);
        }
        tv_chapter_name.setText(Html.fromHtml(StringUtils.formatChapterName(item.getSuperChapterName(), item.getChapterName())));
//        if (item.isCollect()) {
//            cv_collect.setChecked(true, false);
//        } else {
//            cv_collect.setChecked(false, false);
//        }
        tv_chapter_name.setOnClickListener(new OnClickListener2() {
            @Override
            public void onClick2(View v) {
//                KnowledgeArticleActivity.start(v.getContext(),
//                        item.getSuperChapterId(), item.getSuperChapterName(),
//                        item.getChapterId());
            }
        });
        tv_author.setOnClickListener(new OnClickListener2() {
            @Override
            public void onClick2(View v) {
//                UserInfoKt.Companion.start(v.getContext(),item.getUserId());
            }
        });
//        cv_collect.setOnClickListener(new CollectView.OnClickListener() {
//            @Override
//            public void onClick(CollectView v) {
//                if (v.isChecked()) {
//                    if (onCollectListener != null) {
//                        onCollectListener.collect(item, v);
//                    }
//                } else {
//                    if (onCollectListener != null) {
//                        onCollectListener.uncollect(item, v);
//                    }
//                }
//            }
//        });
        view.setOnClickListener(new OnClickListener2() {
            @Override
            public void onClick2(View v) {
//                UrlOpenUtils.Companion.with(item).open(v.getContext());

                mDBhepler.insertNote(item.getTitle(),item.getLink());
                AgentWebAc.start(v.getContext(),item.getLink(),item.getAuthor());
            }
        });
    }

}
