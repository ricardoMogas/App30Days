package com.main.app30daysrmv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.inappmessaging.model.Button
import com.main.app30daysrmv.model.Day
import com.main.app30daysrmv.ui.theme.App30DaysRMVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App30DaysRMVTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen( modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) {
        DayList(days = com.main.app30daysrmv.data.DayList.days, Modifier.padding(it))
    }
}


@Composable
fun DayItem(day: Day, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = day.title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

            }
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp
                )
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(194.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    painter = painterResource(id = day.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = day.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
@Composable
fun DayList(days: List<Day>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
    ) {
        items(days) {
            DayItem(day = it)
        }
    }
}
@Composable
fun TopAppBar() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
            .padding(12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "30 Days RMV",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "main preview"
)
@Composable
fun GreetingPreview() {
    App30DaysRMVTheme {
        MainScreen()
    }
}