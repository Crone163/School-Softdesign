package com.softdesign.school.ui.activities;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingFragment;
import com.softdesign.school.ui.fragments.TaskFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;


public class MainActivity extends AppCompatActivity {


    private static final String FRAGMENT_TAG = "fragment_tag";
    private static final String CHECKED_KEY = "checked";
    private static final String TAG = "MainActivity";
    private static final long TRASH_HOLD_BACK = 3500;

    private Toolbar mToolBar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private Fragment mFragment;
    private Toast pressBackToast;
    private long mLastBackPress;
    private MenuItem mItem;
    private ImageView mImageView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private View mHeaderView;
    private AppBarLayout mAppBar;

    AppBarLayout.LayoutParams params = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lg.e(this.getClass().getSimpleName(), "onCreate()");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
        mAppBar = (AppBarLayout) findViewById(R.id.appbar_layout);
        //задаем программно app:headerLayout, чтобы определить вьюхи внутри navigation_header'a.
        mHeaderView = mNavigationView.inflateHeaderView(R.layout.navigation_header);
        mImageView = (ImageView) mHeaderView.findViewById(R.id.drawerAvatar);
        // задаем круглый bitmap
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.avatar);
        mImageView.setImageBitmap(getCircleBitmap(bm));

        setupToolbar();
        setupDrawer();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container,new ProfileFragment(),FRAGMENT_TAG).commit();
            //устанавливаем цвет выделения первому айтему
            mItem = mNavigationView.getMenu().findItem(R.id.drawer_profile);
            mItem.setCheckable(true);
            mItem.setChecked(true);
        }

        pressBackToast = Toast.makeText(getApplicationContext(), R.string.press_back_again_to_exit,
                Toast.LENGTH_SHORT);

    }

    /**
     *
     * @param collapse - true закрывает CollapseBar, false открывает.
     */
    public void lockAppBar(boolean collapse){
        params = (AppBarLayout.LayoutParams) mCollapsingToolbarLayout.getLayoutParams();
        if(collapse){

            mAppBar.setExpanded(false);
            AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                     if(mCollapsingToolbarLayout.getHeight() + verticalOffset <= ViewCompat.getMinimumHeight(mCollapsingToolbarLayout) + getStatusBarHeight()){
                          params.setScrollFlags(0);
                          mCollapsingToolbarLayout.setLayoutParams(params);
                          mAppBar.removeOnOffsetChangedListener(this);
                      }
                  }
            };
            mAppBar.addOnOffsetChangedListener(mListener);

        } else {
            mAppBar.setExpanded(true);
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED  );
            mCollapsingToolbarLayout.setLayoutParams(params);

        }
    }

    private void setupDrawer(){
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragment = new ProfileFragment();
                        break;

                    case R.id.drawer_contacts:
                        mFragment = new ContactsFragment();
                        break;

                    case R.id.drawer_team:
                        mFragment = new TeamFragment();
                        break;

                    case R.id.drawer_task:
                        mFragment = new TaskFragment();
                        break;

                    case R.id.drawer_setting:
                        mFragment = new SettingFragment();
                        break;
                }

                setChecker(item);

                Toast.makeText(MainActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();

                if (mFragment != null) {
                    //используем фрагмент тег, чтобы определять какой фрагмент вернулся в onBackPressed
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, mFragment, FRAGMENT_TAG).addToBackStack(null).commit();
                }

                mDrawerLayout.closeDrawers();
                return false;
            }


        });


    }

    /** Задаем выделение выбранного пункта меню  */
    private void setChecker(MenuItem item){
        // обязательно нужно использовать setCheckable и setChecked для прорисовки выделения айтема
        // цвет выделения для состояния Checked берем из @drawable/state_list
        mItem.setChecked(false);
        mCollapsingToolbarLayout.setTitle(item.getTitle().toString());
        item.setChecked(true);
        item.setCheckable(true);
        mItem = item;
    }

    private void setupToolbar() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            /*Toast.makeText(MainActivity.this, "Menu Click!", Toast.LENGTH_SHORT).show();*/
            mDrawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.e(this.getClass().getSimpleName(),"onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.e(this.getClass().getSimpleName(), "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getClass().getSimpleName(), "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getClass().getSimpleName(), "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getClass().getSimpleName(), "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getClass().getSimpleName(), "onDestroy()");
    }

    /* Сохраняем текущий выделенные пунк меню  */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.e(this.getClass().getSimpleName(), "onSaveInstanceState()");
        outState.putInt(CHECKED_KEY, mItem.getItemId());
    }

    /*
    Восстанавливаем сохранённое состояние меню айтемов, используем два параметра,
    чтобы отборзатить цвет выделения.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.e(this.getClass().getSimpleName(), "onRestoreInstanceState()");
        mItem = mNavigationView.getMenu().findItem(savedInstanceState.getInt(CHECKED_KEY));
        mItem.setCheckable(true);
        mItem.setChecked(true);

    }

    /*
    Перед вызовом метода, проверяем на количество BackStack, если у нас последний BackStack в истории,
    то показываем пользователю сообщение, что при нажатии кнопки назад можно выйти из приложения.
    Пользователь выходит из приложения при двойном нажатии на кнопку назад в течении 3.5 сек.

    Если история BackStack не пуста, то при нажатии на кнопку назад возвращает предыдущий фрагмент, и ставим чекед в айтемы дравера.
    */
    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
     /*   Toast.makeText(MainActivity.this, String.valueOf(count), Toast.LENGTH_SHORT).show();*/
        if(count == 0){
            long currentTime = System.currentTimeMillis();
            if (Math.abs(currentTime - mLastBackPress) > TRASH_HOLD_BACK) {
                pressBackToast.show();
                mLastBackPress = currentTime;
            } else {
                pressBackToast.cancel();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return;
        }

        super.onBackPressed();

        Fragment backFragment =  getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        Lg.e(TAG, "Found fragment: " + backFragment);

        if(backFragment  instanceof ProfileFragment) {
            mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
        }

        if(backFragment  instanceof ContactsFragment) {
            mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
        }

        if(backFragment  instanceof TeamFragment) {
            mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
        }

        if(backFragment  instanceof TaskFragment) {
            mNavigationView.getMenu().findItem(R.id.drawer_task).setChecked(true);
        }

        if(backFragment  instanceof SettingFragment) {
            mNavigationView.getMenu().findItem(R.id.drawer_setting).setChecked(true);
        }
    }

    /**
     *
     * @param bitmap - перерисовывает квадратную картинку в круг.
     * @return - возвращает скругленный bitmap
     */
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setShadowLayer(5.5f, 6.0f, 6.0f, Color.BLACK);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


}
