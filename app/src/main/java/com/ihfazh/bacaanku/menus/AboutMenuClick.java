package com.ihfazh.bacaanku.menus;

import android.content.Context;
import android.content.Intent;

import com.ihfazh.bacaanku.AboutActivity;
import com.ihfazh.bacaanku.R;

public class AboutMenuClick implements MenuClick{
    @Override
    public Boolean test(int itemId) {
        return itemId == R.id.menu_about;
    }

    @Override
    public void click(Context ctx) {
        Intent intent = new Intent(ctx, AboutActivity.class);
        ctx.startActivity(intent);
    }
}
