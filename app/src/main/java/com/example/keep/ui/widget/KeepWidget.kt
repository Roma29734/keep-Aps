package com.example.keep.ui.widget

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.example.keep.R
import com.example.keep.data.dao.User
import com.example.keep.data.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class KeepWidget : AppWidgetProvider() {

    @Inject
    lateinit var repository: UserRepository

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            repository.readAllData().value?.let {
                updateAppWidget(context, appWidgetManager, appWidgetId,
                    it
                )
            }
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

@SuppressLint("RemoteViewLayout")
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    listKeep: List<User>
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val adapter = KeepAdapter()

    val views = RemoteViews(context.packageName, R.layout.keep_widget)
//    views.setTextViewText(R.id.appwidget_text, widgetText)
    // Instruct the widget manager to update the widget
   
    appWidgetManager.updateAppWidget(appWidgetId, views)
}