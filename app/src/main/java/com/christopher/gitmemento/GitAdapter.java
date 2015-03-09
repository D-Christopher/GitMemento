package com.christopher.gitmemento;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Christopher on 07/03/2015.
 */
public class GitAdapter extends ArrayAdapter<Git>{
    Context context;
    int layoutResourceId;
    Git data[] = null;

    public GitAdapter(Context context, int layoutResourceId, Git[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        GitHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new GitHolder();

            holder.txtName = (TextView)row.findViewById(R.id.txtName);
            holder.txtCommand = (TextView)row.findViewById(R.id.txtCommand);

            row.setTag(holder);
        }
        else
        {
            holder = (GitHolder)row.getTag();
        }

        Git git = data[position];
        holder.txtName.setText(git.name);
        holder.txtCommand.setText(git.command);

        return row;
    }

    static class GitHolder
    {
        TextView txtName;
        TextView txtCommand;
    }
}
