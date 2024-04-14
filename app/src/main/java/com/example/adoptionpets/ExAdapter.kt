package com.example.adoptionpets

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ExAdapter (private val context: Context,
    private val listDataHeader: List<String>,
    private val listDataChild: HashMap<String, List<String>>
    ) : BaseExpandableListAdapter() {

        override fun getGroupCount(): Int {
            return listDataHeader.size
        }

        override fun getChildrenCount(groupPosition: Int): Int {
            return listDataChild[listDataHeader[groupPosition]]?.size ?: 0
        }

        override fun getGroup(groupPosition: Int): Any {
            return listDataHeader[groupPosition]
        }

        override fun getChild(groupPosition: Int, childPosition: Int): Any {
            return listDataChild[listDataHeader[groupPosition]]?.get(childPosition) ?: ""
        }

        override fun getGroupId(groupPosition: Int): Long {
            return groupPosition.toLong()
        }

        override fun getChildId(groupPosition: Int, childPosition: Int): Long {
            return childPosition.toLong()
        }

        override fun hasStableIds(): Boolean {
            return false
        }

        @SuppressLint("InflateParams")
        override fun getGroupView(
            groupPosition: Int,
            isExpanded: Boolean,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            var convertView = convertView
            val headerTitle = getGroup(groupPosition) as String
            if (convertView == null) {
                val inflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                convertView = inflater.inflate(R.layout.faqs, null)
            }
            val lblListHeader = convertView!!.findViewById<TextView>(R.id.parent_text)
            lblListHeader.text = headerTitle
            return convertView
        }

    @SuppressLint("InflateParams")
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val childText = getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.child_items, null)
        }
        val txtListChild = convertView!!.findViewById<TextView>(R.id.child_text)
        txtListChild.text = childText
        return convertView
    }


    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
            return true
        }
    }