package com.christopher.gitmemento;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import me.drakeet.materialdialog.MaterialDialog;


public class MainActivity extends ActionBarActivity {

    MaterialDialog mMaterialDialog;
    private Toolbar toolbar;

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String menu_setup = getResources().getString(R.string.action_setup_help);
        String menu_create = getResources().getString(R.string.action_create);
        String menu_show = getResources().getString(R.string.action_show);
        String menu_revert = getResources().getString(R.string.action_revert);
        String menu_branch = getResources().getString(R.string.action_branch);
        String menu_update = getResources().getString(R.string.action_update);
        String menu_tag = getResources().getString(R.string.action_tag);
        String menu_publish = getResources().getString(R.string.action_publish);
        String menu_useful = getResources().getString(R.string.action_useful);

        String menu_about = getResources().getString(R.string.action_about);

        final String TITLES[] = {menu_setup, menu_create, menu_show, menu_revert, menu_branch,
                           menu_update, menu_tag, menu_publish, menu_useful, menu_about};
        int ICONS[] = {R.mipmap.ic_action_setup, R.mipmap.ic_action_create, R.mipmap.ic_action_show, R.mipmap.ic_action_revert, R.mipmap.ic_action_branch,
                       R.mipmap.ic_action_update, R.mipmap.ic_action_tag, R.mipmap.ic_action_publish, R.mipmap.ic_action_useful, R.mipmap.ic_action_about,};


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MyAdapter(TITLES,ICONS, this);
        mRecyclerView.setAdapter(mAdapter);

        createListView(getResources().getStringArray(R.array.list_header_setup), getResources().getStringArray(R.array.list_command_setup), getResources().getString(R.string.action_setup_help));

        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());

                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    Drawer.closeDrawers();
                    changeList(recyclerView.getChildPosition(child));
                    return true;
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        //Drawer.openDrawer(mRecyclerView);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    private void changeList(int selected) {
        switch (selected) {
            case 1:
                createListView(getResources().getStringArray(R.array.list_header_setup), getResources().getStringArray(R.array.list_command_setup), getResources().getString(R.string.action_setup_help));
                break;
            case 2:
                createListView(getResources().getStringArray(R.array.list_header_create), getResources().getStringArray(R.array.list_command_create), getResources().getString(R.string.action_create));
                break;
            case 3:
                createListView(getResources().getStringArray(R.array.list_header_show), getResources().getStringArray(R.array.list_command_show), getResources().getString(R.string.action_show));
                break;
            case 4:
                createListView(getResources().getStringArray(R.array.list_header_revert), getResources().getStringArray(R.array.list_command_revert), getResources().getString(R.string.action_revert));
                break;
            case 5:
                createListView(getResources().getStringArray(R.array.list_header_branch), getResources().getStringArray(R.array.list_command_branch), getResources().getString(R.string.action_branch));
                break;
            case 6:
                createListView(getResources().getStringArray(R.array.list_header_update), getResources().getStringArray(R.array.list_command_update), getResources().getString(R.string.action_update));
                break;
            case 7:
                createListView(getResources().getStringArray(R.array.list_header_tag), getResources().getStringArray(R.array.list_command_tag), getResources().getString(R.string.action_tag));
                break;
            case 8:
                createListView(getResources().getStringArray(R.array.list_header_publish), getResources().getStringArray(R.array.list_command_publish), getResources().getString(R.string.action_publish));
                break;
            case 9:
                createListView(getResources().getStringArray(R.array.list_header_useful), getResources().getStringArray(R.array.list_command_useful), getResources().getString(R.string.action_useful));
                break;
            case 10:
                mMaterialDialog = new MaterialDialog(this)
                        .setTitle(getResources().getString(R.string.action_about))
                        .setMessage(getResources().getString(R.string.about_txt))
                        .setPositiveButton("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });

                mMaterialDialog.show();

                break;
            default:
                break;
        }
    }

    public void createListView(String[] tabHeader, String[] tabCommand, String name){
        ListView listView1;
        int nbElements = tabHeader.length;
        Git git_data[] = new Git[nbElements];

        for(int i=0; i<nbElements; i++){
            git_data[i] = new Git (tabHeader[i], tabCommand[i]);
        }

        GitAdapter adapterGit = new GitAdapter(this, R.layout.listview_item_row, git_data);

        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(adapterGit);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(name);
    }
}
