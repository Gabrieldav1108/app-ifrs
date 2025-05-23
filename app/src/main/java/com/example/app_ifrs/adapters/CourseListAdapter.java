package com.example.app_ifrs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.app_ifrs.R;

import java.util.HashMap;
import java.util.List;

public class CourseListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groupList;
    private HashMap<String, List<String>> itemMap;
    public HashMap<String, String> courseUrls = new HashMap<>();



    public CourseListAdapter(Context context, List<String> groupList, HashMap<String, List<String>> itemMap) {
        this.context = context;
        this.groupList = groupList;
        this.itemMap = itemMap;
    }

    @Override
    public int getGroupCount() { return groupList.size(); }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemMap.get(groupList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemMap.get(groupList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) { return groupPosition; }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() { return false; }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView groupText = convertView.findViewById(R.id.groupTitle);
        groupText.setText(groupTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // Inflar seu layout simplificado
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView childItem = convertView.findViewById(R.id.childItem);
        String courseText = (String) getChild(groupPosition, childPosition);
        childItem.setText(courseText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public String getCourseUrl(String selectedCourse) {
        return courseUrls.get(selectedCourse.trim());

    }
}
