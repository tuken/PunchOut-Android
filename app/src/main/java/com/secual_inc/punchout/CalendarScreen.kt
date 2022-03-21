package com.secual_inc.punchout

import android.text.format.DateFormat
import android.text.format.DateUtils
import android.text.format.DateUtils.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.secual_inc.punchout.ui.theme.PunchOutTheme
import java.util.*


@Composable
fun CalendarScreen(calViewModel: CalendarViewModel = viewModel()) {

    val observer = remember {

        LifecycleEventObserver { _, event ->

            when (event) {

                Lifecycle.Event.ON_CREATE -> {
                    calViewModel.start();
                }
                Lifecycle.Event.ON_START -> {}
                Lifecycle.Event.ON_RESUME -> {}
                Lifecycle.Event.ON_PAUSE -> {}
                Lifecycle.Event.ON_STOP -> {}
                Lifecycle.Event.ON_DESTROY -> {
                    calViewModel.stop();
                }
                Lifecycle.Event.ON_ANY -> {}
            }
        }
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(Unit) {

        lifecycle.addObserver(observer)

        onDispose {

            lifecycle.removeObserver(observer)
        }
    }

    val date = calViewModel.date.observeAsState("")

    val dow = calViewModel.dow.observeAsState("")

    val time = calViewModel.time.observeAsState("")

    Column {

        Text(
            text = date.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            color = Color.White,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
        )

        Text(
            text = dow.value,
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )

        Text(
            text = time.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            color = Color.White,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
        )
    }
}

class CalendarViewModel : ViewModel() {

    private val timer = Timer()

    private val _date = MutableLiveData("16 Mar, 2022")

    val date: LiveData<String>
        get() = _date

    private val _dow = MutableLiveData("Thursday")

    val dow: LiveData<String>
        get() = _dow

    private val _time = MutableLiveData("18 : 57 : 20")

    val time: LiveData<String>
        get() = _time

    fun start() {

        val tick = object : TimerTask() {

            override fun run() {

                val now = System.currentTimeMillis()

                val dateStr = DateUtils.formatDateTime(null, now, FORMAT_SHOW_YEAR or FORMAT_SHOW_DATE)
                if (dateStr != date.value) {
                    _date.postValue(dateStr)
                }

                val dowStr = DateUtils.formatDateTime(null, now, FORMAT_SHOW_WEEKDAY)
                if (dowStr != dow.value) {
                    _dow.postValue(dowStr)
                }

                val timeStr = DateFormat.format("HH : mm : ss", now) as String
                _time.postValue(timeStr)
            }
        }

        timer.schedule(tick, 0, 1000)
    }

    fun stop() {

        timer.cancel()
    }

    override fun onCleared() {

        super.onCleared()
        stop()
    }
}

@Preview
@Composable
fun CalendarPreview() {

    PunchOutTheme {

        CalendarScreen()
    }
}
